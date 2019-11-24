package com.example.emr.User.Scheduling;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.emr.Helper.DataCustom;
import com.example.emr.Helper.MaskEditUtil;
import com.example.emr.Models.Scheduling;
import com.example.emr.Models.User;
import com.example.emr.R;
import com.example.emr.Services.DataService;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Slide02Activity extends AppCompatActivity {

    private ImageView iValidate, iBack;
    private int day, month, year;
    private EditText etHour;
    DataService service;
    private String nameDoctor, nameCategory, hourSelected, id;
    private MaterialCalendarView calendarioAgendar;
    private Spinner spCategory, spDoctor;
    private String stringURL = "https://ey7li2szf0.execute-api.us-east-1.amazonaws.com/dev/";
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

        iValidate = findViewById( R.id.iShedule );
        iBack = findViewById( R.id.iClose );
        spDoctor=findViewById( R.id.spDoctor );
        etHour = findViewById( R.id.etHour );

        etHour.addTextChangedListener(MaskEditUtil.mask(etHour, MaskEditUtil.FORMAT_HOUR));
        calendarConfig();

        spinnerConfig();

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                nameCategory = parent.getItemAtPosition(posicao).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        retrofitConfig();
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

        iValidate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    hourSelected = etHour.getText().toString();
                    if (hourSelected.isEmpty()){
                        Toast.makeText( Slide02Activity.this, "Nenhum horário selecionado!", Toast.LENGTH_SHORT ).show();
                    } else {
                    String retornoHora[] = hourSelected.split( "/" );
                    int hora = Integer.parseInt(   retornoHora[0]   );
                    int minuto = Integer.parseInt(   retornoHora[1]   );


                    long data = System.currentTimeMillis();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "d/M/yyyy" );
                    String dataAtual = simpleDateFormat.format( data );

                    if(hora >= 8 && hora < 17  && minuto >= 0 && minuto < 61){
                        if(!nameCategory.equals( R.string.categoria )){
                            String dataFormat = hourSelected + " " + day + "/" + month + "/" + year;
                            Scheduling agendarObject = new Scheduling( id, nameCategory, nameDoctor,  dataFormat );
                            retrofitConfig();
                            Call<Scheduling> chm = service.agendar( agendarObject );
                            chm.enqueue( new Callback<Scheduling>() {
                                @Override
                                public void onResponse(Call<Scheduling> call, Response<Scheduling> response) {
                                    Toast.makeText( Slide02Activity.this, "Agendamento feito com sucesso!", Toast.LENGTH_SHORT ).show();
                                    System.out.println( "Agendamento feito com sucesso" );
                                }
                                @Override
                                public void onFailure(Call<Scheduling> call, Throwable t) {

                                }
                            });

                        } else{
                            Toast.makeText( Slide02Activity.this, "Você não selecionou uma categoria!", Toast.LENGTH_SHORT ).show();
                        }
                    } else{
                            Toast.makeText( Slide02Activity.this, "Horário Inválido. Tente novamente.", Toast.LENGTH_SHORT ).show();
                    }
                }
            }
        } );
    }

    public void calendarConfig(){
        calendarioAgendar = findViewById( R.id.calAgendar);
        calendarioAgendar.state().edit()
                .setFirstDayOfWeek( Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2019, 9, 1))
                .setMaximumDate(CalendarDay.from(2020, 0, 1))
                .setCalendarDisplayMode( CalendarMode.MONTHS)
                .commit();

        calendarioAgendar.setTitleFormatter(new MonthArrayTitleFormatter(getResources().getTextArray(R.array.tit_meses)));
        calendarioAgendar.setWeekDayFormatter(new ArrayWeekDayFormatter(getResources().getTextArray(R.array.tit_semanas)));

        calendarioAgendar.setOnDateChangedListener( new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                day = date.getDay();
                month = date.getMonth()+1;
                year = date.getYear();
            }
        });
    }

    public void retrofitConfig(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(stringURL)
                .addConverterFactory( GsonConverterFactory.create())
                .build();
        service = retrofit.create(DataService.class);
    }

    public void spinnerConfig(){
        spCategory = findViewById( R.id.spCategory );
        ArrayAdapter<CharSequence> adapterCatorgory = ArrayAdapter.createFromResource(this,
                R.array.categorias, android.R.layout.simple_spinner_item);
        adapterCatorgory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapterCatorgory);
    }
}