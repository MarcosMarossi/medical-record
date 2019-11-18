package com.example.emr.Nurse;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
    private ArrayList<User> users;
    private Retrofit retrofit;
    private String token,mac;
    private EditText edtPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_list_patient);
        retrofit = new Retrofit.Builder()
                .baseUrl(stringURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final DataService service = retrofit.create(DataService.class);
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

        listaPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User u = (User)listaPacientes.getItemAtPosition(i);



                //Toast.makeText(ActivityListPatient.this,u.getPassword(),Toast.LENGTH_LONG).show();

            }
        });


    }
}
