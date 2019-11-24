package com.example.emr.User.Scheduling;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.emr.Config.RetrofitConfig;
import com.example.emr.Helper.MaskEditUtil;
import com.example.emr.Models.Scheduling;
import com.example.emr.R;
import com.example.emr.Services.DataService;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Slide02Activity extends AppCompatActivity {

    private FloatingActionButton iValidate, iBack;
    private EditText etHour, etDate;
    private String nameDoctor, nameCategory, hourSelected, id, dateSelected;
    private Spinner spCategory, spDoctor;
    private Retrofit retrofit;
    private List<Scheduling> shedulings = new ArrayList<>(  );
    private ArrayList<String> playerNames = new ArrayList<String>();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.s_slide02 );
        getSupportActionBar().hide();

        sharedPreferences = getSharedPreferences("salvarToken", MODE_PRIVATE);
        id = sharedPreferences.getString( "id", null );
        spCategory = findViewById( R.id.spCategory );
        iValidate = findViewById( R.id.fabConfirm );
        iBack = findViewById( R.id.fabBack );
        spDoctor=findViewById( R.id.spDoctor );
        etHour = findViewById( R.id.etHour );
        etDate = findViewById( R.id.etDate );

        /**
         * Configure Hour/Date
         */

        etHour.addTextChangedListener(MaskEditUtil.mask(etHour, MaskEditUtil.FORMAT_HOUR));
        etDate.addTextChangedListener(MaskEditUtil.mask(etDate, MaskEditUtil.FORMAT_DATE));


        /**
        * Configure Doctor Spinner
         */

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categorias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);

        retrofit = RetrofitConfig.retrofitConfig();
        DataService service = retrofit.create( DataService.class );

        Call<List<Scheduling>> call = service.pegarId();
        call.enqueue(new Callback<List<Scheduling>>() {
            @Override
            public void onResponse(Call<List<Scheduling>> call, Response<List<Scheduling>> response) {
                if(response.isSuccessful()){

                    shedulings = response.body();

                    for (int i = 0; i < shedulings.size(); i++){
                        Scheduling s = shedulings.get( i );
                        playerNames.add(s.getName());
                    }
                }
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Slide02Activity.this, android.R.layout.simple_spinner_item, playerNames);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                spDoctor.setAdapter(spinnerArrayAdapter);
            }
            @Override
            public void onFailure(Call<List<Scheduling>> call, Throwable t) {
                Log.d("404","Ocorreu um erro: " + t);
            }
        });

        /**
         * Configure Scheduling System
         */

        iValidate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hourSelected = etHour.getText().toString();
                dateSelected = etDate.getText().toString();

                if (hourSelected.isEmpty()){
                    Toast.makeText( Slide02Activity.this, "Nenhum horário selecionado!", Toast.LENGTH_SHORT ).show();
                } else {
                    String dataFormat = hourSelected + " " + dateSelected;
                    Scheduling agendarObject = new Scheduling( id, nameCategory, nameDoctor,  dataFormat );

                    retrofit = RetrofitConfig.retrofitConfig();
                    DataService service1 = retrofit.create( DataService.class );
                    Call<Scheduling> chm = service1.agendar( agendarObject );
                    chm.enqueue( new Callback<Scheduling>() {
                        @Override
                        public void onResponse(Call<Scheduling> call, Response<Scheduling> response) {
                            Toast.makeText( Slide02Activity.this, "Agendamento feito com sucesso!", Toast.LENGTH_SHORT ).show();
                        }
                        @Override
                        public void onFailure(Call<Scheduling> call, Throwable t) {

                        }
                    });
                }
            }
        } );

        iBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getApplicationContext(), Slide01Activity.class ) );
            }
        } );
    }
}