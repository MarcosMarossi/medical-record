package com.example.emr.User;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    private DataService service;
    private RecyclerView recyclerView;
    private List<Scheduling> fotodope = new ArrayList<>(  );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_calendar);


        calendario = findViewById(R.id.calHistorico);
        calendario.state().edit()
                .setMaximumDate(CalendarDay.from(2019,1,1))
                .setMaximumDate(CalendarDay.from(2020,6,1))
                .commit();

        calendario.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                Log.i("data: ", "valor: " + (date.getMonth() + 1) + "/" + date.getYear());
            }
        });

        retrofit = RetrofitConfig.retrofitConfig();
        service = retrofit.create( DataService.class);
        Call<Test> call = service.historico("11/2019");

        call.enqueue( new Callback<Test>() {
            @Override
            public void onResponse(Call<Test> call, Response<Test>response) {

                try{

                } catch (NullPointerException e){
                    System.out.println( e + "fehfeu7rfhweh3yr79eg" );
                }

                Test scheduling;
                scheduling = response.body();
                fotodope = scheduling.schedules;

                for(int i = 0; i < fotodope.size();i++){
                    System.out.println( fotodope.get( i ).getPatient() );

                }

            }

            @Override
            public void onFailure(Call<Test> call, Throwable t) {

            }
        } );

        fabAgendar = findViewById(R.id.fabAgendar);
        fabAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirAgendar();
            }
        });

        configurarRecyclerView();

    }
    private void abrirAgendar(){
        Intent intent = new Intent( HistoryActivity.this, Slide01Activity.class);
        startActivity(intent);
    }

    public void configurarRecyclerView() {

        ListView listaDeCursos = (ListView) findViewById(R.id.listHistory);

        ArrayAdapter<Scheduling> adapter = new ArrayAdapter<Scheduling>(this, android.R.layout.simple_list_item_1, fotodope);

        listaDeCursos.setAdapter(adapter);

    }
}
