package com.example.emr.User.Slide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.emr.Models.Date;
import com.example.emr.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class SlideSecond extends AppCompatActivity {

    private MaterialCalendarView calendarioAgendar;
    private ImageView guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_slide01);

        getSupportActionBar().hide();

        guardar = findViewById(R.id.imgAvancar);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    public void onMonthChanged(MaterialCalendarView widget, final CalendarDay date) {

                        Date data = new Date();

                        data.setDay(Integer.toString(date.getDay()));
                        data.setMonth(Integer.toString(date.getMonth()+1));
                        data.setYear(Integer.toString(date.getYear()));

                        Toast.makeText(SlideSecond.this, "Month: " + data.getMonth() + "Year: " + data.getYear(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SlideSecond.this, SlideUltimate.class);
                        startActivity(intent);

                    }
                });
            }
        });

    }

    public void avancar(View view){




    }
    public void voltar(View view){
        Intent intent = new Intent(SlideSecond.this, SlideInitial.class);
        startActivity(intent);
    }
}
