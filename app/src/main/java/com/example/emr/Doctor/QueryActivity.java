package com.example.emr.Doctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.emr.Adapter.ScheduleAdapter;
import com.example.emr.Config.RetrofitConfig;
import com.example.emr.Models.Scheduling;
import com.example.emr.Models.Test;
import com.example.emr.R;
import com.example.emr.Services.DataService;
import com.example.emr.User.HistoryActivity;
import com.example.emr.User.RecyclerItemClickListener;
import com.example.emr.User.Scheduling.RecordUserActivity;
import com.example.emr.User.Scheduling.Slide01Activity;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QueryActivity extends AppCompatActivity {

    private MaterialCalendarView calendario;
    private FloatingActionButton fabAgendar;
    private Retrofit retrofit;
    private Test test;
    private String mesSelecionado, anoSelecionado, idSchedule;
    private DataService service;
    private RecyclerView recyclerView;
    private List<Scheduling> fotodope = new ArrayList<>(  );
    private ScheduleAdapter scheduleAdapter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docact_consultas);


        sharedPreferences = getSharedPreferences("salvarToken", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        recyclerView = findViewById( R.id.recyclerView );
        fabAgendar = findViewById(R.id.fabAgendar);
        idSchedule = sharedPreferences.getString("idSchedule", null);

        getItems();
    }

    public void getItems(){

        retrofit = RetrofitConfig.retrofitConfig();
        service = retrofit.create( DataService.class);
        Call<Test> call = service.historicPatient(mesSelecionado,anoSelecionado);

        call.enqueue( new Callback<Test>() {
            @Override
            public void onResponse(Call<Test> call, Response<Test> response) {
                test = response.body();
                fotodope = test.schedules;
                for(int i = 0; i < fotodope.size();i++){
                    System.out.println( fotodope.get( i ).getPatient() );
                }
                recyclerView();
            }

            @Override
            public void onFailure(Call<Test> call, Throwable t) {

            }
        });
    }

    public void recyclerView() {

        scheduleAdapter = new ScheduleAdapter(fotodope, this);
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter( scheduleAdapter );

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                scheduleAdapter.notifyDataSetChanged();

                                Scheduling video = fotodope.get(position);
                                idSchedule = video.get_id();
                                editor.putString( "idRecord", idSchedule );
                                editor.commit();
                                Toast.makeText( getApplicationContext(), idSchedule, Toast.LENGTH_SHORT ).show();
                                startActivity( new Intent( getApplicationContext(), RecordUserActivity.class ) );
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
