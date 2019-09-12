package com.example.emr.Slide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.emr.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class Calendar extends AppCompatActivity {

    private MaterialCalendarView calendarioAgendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_agendar_cal);

        getSupportActionBar().hide();


        calendarioAgendar = findViewById(R.id.calAgendar);
        calendarioAgendar.state().edit()
                .setMaximumDate(CalendarDay.from(2019,1,1))
                .setMaximumDate(CalendarDay.from(2020,6,1))
                .commit();
        CharSequence meses[] = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
        calendarioAgendar.setTitleMonths(meses);

        CharSequence dias[] = {"Seg","Ter","Qua","Qui","Sex","Sab","Dom"};
        calendarioAgendar.setWeekDayLabels(dias);

        //recupera as datas selecionadas
        calendarioAgendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                Log.i("data: ", "valor: " + (date.getMonth() + 1) + "/" + date.getYear());
            }
        });

    }

    public void avancar(View view){
        Intent intent = new Intent(Calendar.this,Ultimo.class);
        startActivity(intent);
    }
    public void voltar(View view){
        Intent intent = new Intent(Calendar.this,Agendar.class);
        startActivity(intent);
    }
}
