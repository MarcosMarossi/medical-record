package com.example.emr.Doctor;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.emr.Adapter.DoctorAdapter;
import com.example.emr.Config.RetrofitConfig;
import com.example.emr.Models.ArraySchedule;
import com.example.emr.Models.Schedule;
import com.example.emr.Models.Scheduling;
import com.example.emr.Models.User;
import com.example.emr.R;
import com.example.emr.Services.DataService;
import com.example.emr.User.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecordsActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private ArraySchedule arraySchedule;
    private String mesSelecionado, anoSelecionado, nameMedic;
    private DataService service;
    private RecyclerView recyclerView;
    private List<Scheduling> fotodope = new ArrayList<>(  );
    private List<User> fotodamao = new ArrayList<>(  );
    private DoctorAdapter doctorAdapter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docact_warnings);

        sharedPreferences = getSharedPreferences("salvarToken", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        recyclerView = findViewById( R.id.recyclerView );
        nameMedic = sharedPreferences.getString("name", null);
        System.out.println("nome medico:" + nameMedic );

        getItems();
        Toast.makeText( this, "Olá mundo.", Toast.LENGTH_SHORT ).show();
    }

    public void getItems(){

        System.out.println( "oi" );

        retrofit = RetrofitConfig.retrofitConfig();
        service = retrofit.create( DataService.class);
        Call<Schedule> call = service.scheduleByName("Roberto Raul Oliveira");

        call.enqueue( new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {

                System.out.println( "Cheguei" );

                Schedule ls = response.body();

                fotodope = ls.schedule;
                fotodamao = ls.patient;

                for(int i = 0 ; i < fotodope.size(); i++){
                    System.out.println( fotodope.get( i ).getDate() );
                }

                for(int i = 0 ; i < fotodamao.size(); i++){
                    System.out.println( fotodamao.get( i ).getName() );
                }

                recyclerView();
            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable t) {

            }
        });
    }

    public void recyclerView() {

        doctorAdapter = new DoctorAdapter(fotodope, fotodamao, this);
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter( doctorAdapter );

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                doctorAdapter.notifyDataSetChanged();

                                Scheduling video = fotodope.get(position);
                                nameMedic = video.get_id();
                                Toast.makeText( getApplicationContext(), nameMedic, Toast.LENGTH_SHORT ).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
    }
}
