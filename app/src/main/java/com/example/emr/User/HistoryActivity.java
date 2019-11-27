package com.example.emr.User;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.example.emr.Adapter.AdapterScheduling;
import com.example.emr.Config.RetrofitConfig;
import com.example.emr.Models.Scheduling;
import com.example.emr.Models.Test;
import com.example.emr.R;
import com.example.emr.Services.DataService;
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

public class HistoryActivity extends AppCompatActivity {

    private MaterialCalendarView calendario;
    private FloatingActionButton fabAgendar;
    private Retrofit retrofit;
    private Test test;
    private String mesSelecionado, anoSelecionado;
    private DataService service;
    private RecyclerView recyclerView;
    private List<Scheduling> fotodope = new ArrayList<>(  );
    private Boolean STATS_CALL = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_calendar);

        recyclerView = findViewById( R.id.recycler );
        fabAgendar = findViewById(R.id.fabAgendar);

        calendario = findViewById(R.id.calHistorico);
        calendario.state().edit()
                .setMaximumDate(CalendarDay.from(2019,1,1))
                .setMaximumDate(CalendarDay.from(2020,6,1))
                .commit();

        CalendarDay calendarDay = calendario.getCurrentDate();
        mesSelecionado = String.format( "%02d", (calendarDay.getMonth()+1) );
        anoSelecionado = Integer.toString( calendarDay.getYear());

        calendario.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

                mesSelecionado = String.format( "%02d", (date.getMonth()+1) );
                anoSelecionado =  Integer.toString( date.getYear() );
                getItems();

            }
        });

        fabAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( HistoryActivity.this, Slide01Activity.class));
            }
        });

        getItems();


    }



    public void getItems(){

        retrofit = RetrofitConfig.retrofitConfig();
        service = retrofit.create( DataService.class);
        Call<Test> call = service.historico(mesSelecionado,anoSelecionado);

        call.enqueue( new Callback<Test>() {
            @Override
            public void onResponse(Call<Test> call, Response<Test>response) {
                test = response.body();
                fotodope = test.schedules;


                for(int i = 0; i < fotodope.size();i++){
                    System.out.println( fotodope.get( i ).getPatient() );
                }
                configurarRecyclerView();
            }

            @Override
            public void onFailure(Call<Test> call, Throwable t) {

            }
        });
    }

    public void configurarRecyclerView() {

        AdapterScheduling adapter;

        adapter = new AdapterScheduling(fotodope, this);
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter( adapter );

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Scheduling video = fotodope.get(position);
                                String idVideo = video.getPatient();

                                Intent i = new Intent(HistoryActivity.this, Prontuario.class);
                                i.putExtra("idVideo", idVideo );
                                startActivity(i);

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
