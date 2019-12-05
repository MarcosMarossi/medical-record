package com.example.emr.Doctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

public class Act_Patient_BPM extends AppCompatActivity {
    private String stringURL = "https://ey7li2szf0.execute-api.us-east-1.amazonaws.com/dev/";
    private ArrayList<User> users;
    private Retrofit retrofitPaciente;
    private EditText edtPatient;
    private ListView lvPacientes;
    private Button btnBusca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_patient_list);

        users = new ArrayList<>();
        edtPatient = (EditText)findViewById(R.id.editTextPacientesUp);
        lvPacientes = (ListView)findViewById(R.id.lvPacientesUp);
        btnBusca = (Button)findViewById(R.id.btnBusca);

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
                final AdapterUser adapter = new AdapterUser(users, Act_Patient_BPM.this);
                adapter.notifyDataSetChanged();
                lvPacientes.setAdapter(adapter);

                lvPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //User u = (User)lvPacientes.getItemAtPosition(i);
                        //u.setUrl(stringURL2+mac);

                        //confirmarBPM(u,u.getName(),service);
                        lvPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                User u = (User)lvPacientes.getItemAtPosition(i);
                                //u.setUrl(stringURL2+mac);
                                String id = u.getId();
                                //confirmarBPM(u,u.getName(),service);
                                Intent intent = new Intent(getApplicationContext(),LogDoctor.class).putExtra("id",id);
                                startActivity(intent);
                                //finish();

                            }
                        });

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
                AdapterUser adapterUser = new AdapterUser(user,Act_Patient_BPM.this);
                adapterUser.notifyDataSetChanged();
                lvPacientes.setAdapter(adapterUser);



                lvPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        User u = (User)lvPacientes.getItemAtPosition(i);
                        //u.setUrl(stringURL2+mac);
                        String id = u.getId();
                        //confirmarBPM(u,u.getName(),service);
                        startActivity(new Intent(getApplicationContext(),LogDoctor.class).putExtra("id",u.getId()));

                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
    }
}
