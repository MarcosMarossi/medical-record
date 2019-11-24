package com.example.emr.Nurse;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.emr.Models.Heartbeat;
import com.example.emr.Models.User;
import com.example.emr.R;
import com.example.emr.Services.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityListPatient extends AppCompatActivity {

    private String stringURL = "https://ey7li2szf0.execute-api.us-east-1.amazonaws.com/dev/";
    private String stringURL2 = "https://ltc7q76qp5.execute-api.us-east-1.amazonaws.com/dev/heartbeat/";
    private ArrayList<User> users;
    private Retrofit retrofitPaciente,retrofitHeartbeat;
    private EditText edtPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_list_patient);

        final String mac = getIntent().getExtras().getString("mac");
        //Toast.makeText(ActivityListPatient.this,mac,Toast.LENGTH_SHORT).show();

        //Retrofit Paciente
        retrofitPaciente = new Retrofit.Builder()
                .baseUrl(stringURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final DataService service = retrofitPaciente.create(DataService.class);
        users = new ArrayList<>();

        final ListView listaPacientes = (ListView)findViewById(R.id.listPatient);
        edtPaciente = (EditText)findViewById(R.id.edtPaciente);
        final ArrayAdapter<User> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,users);

        Call<ArrayList<User>> fillDialog = service.getAllPatients();

        fillDialog.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                users = response.body();
                for(User u:users){
                    adapter.add(u);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(ActivityListPatient.this, "Ocorreu um erro na requisição", Toast.LENGTH_SHORT).show();
            }
        });

        listaPacientes.setAdapter(adapter);
        ////////////////////////////////////////////////////////////////////////
        ///////Retrofit BPM/////////////
        retrofitHeartbeat = new Retrofit.Builder()
                .baseUrl(stringURL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DataService serviceHeartbeat = retrofitHeartbeat.create(DataService.class);
        Call <Heartbeat> call = serviceHeartbeat.getBPM(mac);
        call.enqueue(new Callback<Heartbeat>() {
            @Override
            public void onResponse(Call<Heartbeat> call, Response<Heartbeat> response) {
                final Heartbeat heartbeat = response.body();
                Toast.makeText(ActivityListPatient.this,heartbeat.getResult(),Toast.LENGTH_SHORT).show();

                listaPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        User u = (User)listaPacientes.getItemAtPosition(i);
                        u.setUrl(stringURL2+mac);

                        Toast.makeText(ActivityListPatient.this,u.getUrl(),Toast.LENGTH_LONG).show();
                        confirmarBPM(u,heartbeat.getResult(),u.getName(),u.getUrl(),service,u.getId());
                    }
                });
            }

            @Override
            public void onFailure(Call<Heartbeat> call, Throwable t) {
                Toast.makeText(ActivityListPatient.this, "Ocorreu um erro na requisição", Toast.LENGTH_SHORT).show();

            }
        });


        edtPaciente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void confirmarBPM(final User u,String bpm, String nome, final String url, final DataService dataService, final String id){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Confirmação");
        dialog.setMessage("Deseja gravar o valor "+bpm+" no paciente "+nome);
        dialog.setPositiveButton(R.string.sair_sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Call<User> call = dataService.setBPMUser(u);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(ActivityListPatient.this,"Registrado com Sucesso",Toast.LENGTH_LONG).show();
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(ActivityListPatient.this,"Deu ruim",Toast.LENGTH_LONG).show();


                    }
                });
            }
        });

        dialog.setNegativeButton(R.string.sair_nao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ActivityListPatient.this,"OK",Toast.LENGTH_LONG).show();
            }
        });

        dialog.create();
        dialog.show();

    }
}
