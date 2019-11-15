package com.example.emr.User.Scheduling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.emr.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class Slide02Activity extends AppCompatActivity {

    ImageView iNext, iBack;
    private MaterialCalendarView calendarioAgendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.s_slide02 );

        getSupportActionBar().hide();

        iBack = findViewById( R.id.iBack );
        iNext = findViewById( R.id.iNext );

        iNext.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarioAgendar = findViewById(R.id.calAgendar);
                calendarioAgendar.state().edit()
                        .setMaximumDate(CalendarDay.from(2019,1,1))
                        .setMaximumDate( CalendarDay.from(2020,6,1))
                        .commit();
                CharSequence meses[] = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
                calendarioAgendar.setTitleMonths(meses);

                CharSequence dias[] = {"Seg","Ter","Qua","Qui","Sex","Sab","Dom"};
                calendarioAgendar.setWeekDayLabels(dias);
                calendarioAgendar.setOnMonthChangedListener(new OnMonthChangedListener() {
                    @Override
                    public void onMonthChanged(MaterialCalendarView widget, final CalendarDay date) {
                        Intent it = new Intent( getApplicationContext(), Slide03Activity.class );

                        it.putExtra( "dia", date.getDay() );
                        it.putExtra( "mes", date.getMonth() );
                        it.putExtra( "year", date.getYear() );
                        startActivity( it );
                    }
                });

            }
        } );

        iBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Slide02Activity.this, Slide01Activity.class);
                startActivity(it);
            }
        } );

    }
}
