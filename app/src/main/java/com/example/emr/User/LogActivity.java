package com.example.emr.User;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.emr.Adapter.RecyclerAdapter;
import com.example.emr.Models.User;
import com.example.emr.R;
import com.example.emr.Services.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private String stringURL = "https://ey7li2szf0.execute-api.us-east-1.amazonaws.com/dev/";
    SharedPreferences sharedPreferences;
    private ArrayList<User> users;
    private ImageView ivGraph;
    private RecyclerView rvLog;
    RecyclerAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_log);

        users = new ArrayList<>();

        ivGraph = (ImageView)findViewById(R.id.ivGraph);
        rvLog = (RecyclerView)findViewById(R.id.rvLog);
        rvLog.setLayoutManager(new LinearLayoutManager(this));

        sharedPreferences = getSharedPreferences("salvarToken",MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);

        retrofit = new Retrofit.Builder()
                .baseUrl(stringURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final DataService service = retrofit.create(DataService.class);

        Call<ArrayList<User>> call = service.getLog(id);

        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                users = response.body();
                recyclerAdapter = new RecyclerAdapter(LogActivity.this,users);

                recyclerAdapter.notifyDataSetChanged();
                rvLog.setAdapter(recyclerAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });



        String urlGraph = stringURL+"api/heartbeatlog/"+id+"?graph=true";
        Picasso.get().load(urlGraph).into(ivGraph);
    }
}
