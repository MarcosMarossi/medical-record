package com.example.emr.Nurse;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.emr.Adapter.AdapterUser;
import com.example.emr.Models.User;
import com.example.emr.R;
import com.example.emr.Services.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActPatientList extends AppCompatActivity {


    private String stringURL = "https://ey7li2szf0.execute-api.us-east-1.amazonaws.com/dev/";
    private String stringURL2 = "https://ltc7q76qp5.execute-api.us-east-1.amazonaws.com/dev/heartbeat/";
    private ArrayList<User> users;
    private Retrofit retrofitPaciente;
    private EditText edtPatient;
    private ListView lvPacientes;
    private Button btnBusca;
    private String mac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_patient_list);


        users = new ArrayList<>();

        mac = getIntent().getExtras().getString("mac");
        edtPatient = (EditText)findViewById(R.id.editTextPacientesUp);
        lvPacientes = (ListView)findViewById(R.id.lvPacientesUp);
        btnBusca = (Button)findViewById(R.id.btnBusca);

        // final ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,android.R.layout.simple_list_item_1,users);


        retrofitPaciente = new Retrofit.Builder()
                .baseUrl(stringURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final DataService service = retrofitPaciente.create(DataService.class);


        Call<ArrayList<User>> call = service.getAllPatients();

        btnBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizarLista(edtPatient.getText().toString(),service,lvPacientes);
            }
        });



        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                users = response.body();

                /**for(User u:users){
                 adapter.add(u);
                 }**/
                final AdapterUser adapter = new AdapterUser(users,ActPatientList.this);
                adapter.notifyDataSetChanged();
                lvPacientes.setAdapter(adapter);

                lvPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        User u = (User)lvPacientes.getItemAtPosition(i);
                        u.setUrl(stringURL2+mac);

                        confirmarBPM(u,u.getName(),service);

                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
    }

    public void atualizarLista(String cpf, final DataService service, final ListView lvPacientes){
        Call<ArrayList<User>> call = service.getPatientCPF(cpf);
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                ArrayList<User> user = response.body();
                AdapterUser adapterUser = new AdapterUser(user,ActPatientList.this);
                adapterUser.notifyDataSetChanged();
                lvPacientes.setAdapter(adapterUser);



                lvPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        User u = (User)lvPacientes.getItemAtPosition(i);
                        u.setUrl(stringURL2+mac);
                        Toast.makeText(ActPatientList.this,u.getUrl(),Toast.LENGTH_LONG).show();
                        confirmarBPM(u,u.getName(),service);

                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
    }


    public void confirmarBPM(final User u, String nome, final DataService dataService){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Confirmação");
        dialog.setMessage("Deseja gravar registrar o equipamento ao paciente "+nome);
        dialog.setPositiveButton(R.string.sair_sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Call<User> call = dataService.setBPMUser(u);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(ActPatientList.this,"Registrado com Sucesso",Toast.LENGTH_LONG).show();
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(ActPatientList.this,"Deu ruim",Toast.LENGTH_LONG).show();


                    }
                });
            }
        });

        dialog.setNegativeButton(R.string.sair_nao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ActPatientList.this,"OK", Toast.LENGTH_LONG).show();
            }
        });

        dialog.create();
        dialog.show();

    }
}
