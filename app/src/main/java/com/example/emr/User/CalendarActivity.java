package com.example.emr.User;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.emr.Models.Scheduling;
import com.example.emr.R;
import com.example.emr.Services.DataService;
import com.example.emr.User.Scheduling.Slide01Activity;
import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CalendarActivity extends AppCompatActivity {

    private MaterialCalendarView calendario;
    private FloatingActionButton fabAgendar;
    private DataService service;
    private String stringURL = "https://ey7li2szf0.execute-api.us-east-1.amazonaws.com/dev/";
    private ArrayList<Scheduling> shedulings;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(stringURL)
                .addConverterFactory( GsonConverterFactory.create())
                .build();
        service = retrofit.create( DataService.class);
        Call<ArrayList<Scheduling>> call = service.historico( "11/2019" );

        call.enqueue( new Callback<ArrayList<Scheduling>>() {
            @Override
            public void onResponse(Call<ArrayList<Scheduling>> call, Response<ArrayList<Scheduling>> response) {



                shedulings = response.body();
                System.out.println( "nhfdeefhuewuih cheguei" );



                try{
                    for (int i = 0; i < shedulings.size(); i++) {

                        //for(Scheduling s : shedulings){

                        Scheduling s = shedulings.get( i );
                        System.out.println( s.getDate() + ", " + s.getPatient() );

                    }
                } catch (NullPointerException e){
                    System.out.println( "oi linda" + e );
                }


               // Toast.makeText( CalendarActivity.this,shedulings.get(0)+"",Toast.LENGTH_LONG ).show();

            }

            @Override
            public void onFailure(Call<ArrayList<Scheduling>> call, Throwable t) {

            }
        } );

        fabAgendar = findViewById(R.id.fabAgendar);
        fabAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirAgendar();
            }
        });

    }
    private void abrirAgendar(){
        Intent intent = new Intent(CalendarActivity.this, Slide01Activity.class);
        startActivity(intent);
    }


}
