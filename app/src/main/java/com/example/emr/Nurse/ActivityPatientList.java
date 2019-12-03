package com.example.emr.Nurse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.example.emr.Adapter.UserRecyclerAdapter;
import com.example.emr.Models.User;
import com.example.emr.R;
import com.example.emr.Services.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityPatientList extends AppCompatActivity {


    private String stringURL = "https://ey7li2szf0.execute-api.us-east-1.amazonaws.com/dev/";
    private String stringURL2 = "https://ltc7q76qp5.execute-api.us-east-1.amazonaws.com/dev/heartbeat/";
    private ArrayList<User> users;
    private Retrofit retrofitPaciente,retrofitHeartbeat;
    private EditText edtPatient;
    private RecyclerView rvPatients;
    UserRecyclerAdapter userRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_patient_list);


        users = new ArrayList<>();

        edtPatient = (EditText)findViewById(R.id.edtPatient);
        rvPatients = (RecyclerView)findViewById(R.id.rvPatients);
        rvPatients.setLayoutManager(new LinearLayoutManager(this));

        retrofitPaciente = new Retrofit.Builder()
                .baseUrl(stringURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final DataService service = retrofitPaciente.create(DataService.class);

        Call<ArrayList<User>> call = service.getAllPatients();

        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                users = response.body();
                userRecyclerAdapter = new UserRecyclerAdapter(ActivityPatientList.this,users);
                userRecyclerAdapter.notifyDataSetChanged();
                rvPatients.setAdapter(userRecyclerAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
    }
}
