package com.example.emr.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.emr.Models.Date;
import com.example.emr.User.MenuUsrActivity;
import com.example.emr.R;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class SliderActivity extends IntroActivity {

    private MaterialCalendarView calendarioAgendar;
    private ImageView btnAgendar;
    private Spinner hospitais, medicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnAgendar = findViewById(R.id.imgAgendar);

        getSupportActionBar().hide();
        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.slide00)
                .background(R.color.colorPrimary)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.slide01)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.slide02)
                .canGoForward( false )
                .build()
        );
        selecionarItens();


    }



    public void agendarConslta(View v){

        calendarioAgendar = findViewById(R.id.calAgendar);
        calendarioAgendar.state().edit()
                .setMaximumDate(CalendarDay.from(2019,1,1))
                .setMaximumDate(CalendarDay.from(2020,6,1))
                .commit();
        CharSequence meses[] = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
        calendarioAgendar.setTitleMonths(meses);

        CharSequence dias[] = {"Seg","Ter","Qua","Qui","Sex","Sab","Dom"};
        calendarioAgendar.setWeekDayLabels(dias);
        calendarioAgendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, final CalendarDay date) {
                Date data = new Date();
                data.setDay(Integer.toString(date.getDay()));
                data.setMonth(Integer.toString(date.getMonth()+1));
                data.setYear(Integer.toString(date.getYear()));
            }
        });
    }

    public void fechar(View v){
        Intent it = new Intent(SliderActivity.this, MenuUsrActivity.class);
        startActivity(it);
    }

    public void selecionarItens(){

        hospitais = findViewById(R.id.spnCategoria);
        ArrayAdapter <String> adapterCat = new ArrayAdapter<>(this,R.layout.slide02,getResources().
                getStringArray(R.array.categorias));
        adapterCat.setDropDownViewResource( android.R.layout.simple_spinner_item);
    }
}
