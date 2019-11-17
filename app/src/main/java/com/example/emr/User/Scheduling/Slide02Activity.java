package com.example.emr.User.Scheduling;

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

import com.example.emr.Models.MaskEditUtil;
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
import retrofit2.converter.gson.GsonConverterFactory;

public class Slide02Activity extends AppCompatActivity {

    private ImageView iValidate, iBack;
    private int day, month, year;
    private EditText etHour;
    private String nameDoctor, nameCategory, hourSelected;
    private MaterialCalendarView calendarioAgendar;
    private Spinner spCategory, spDoctor;
    private String stringURL = "https://ey7li2szf0.execute-api.us-east-1.amazonaws.com/dev/";
    private List<Scheduling> shedulings = new ArrayList<>(  );
    private ArrayList<String> playerNames = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.s_slide02 );
        getSupportActionBar().hide();


        iValidate = findViewById( R.id.iShedule );
        iBack = findViewById( R.id.iClose );
        spDoctor=findViewById( R.id.spDoctor );
        etHour = findViewById( R.id.etHour );

        calendarConfig();

        spCategory = findViewById( R.id.spCategory );
        ArrayAdapter<CharSequence> adapterCatorgory = ArrayAdapter.createFromResource(this,
                R.array.categorias, android.R.layout.simple_spinner_item);
        adapterCatorgory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapterCatorgory);

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(stringURL)
                .addConverterFactory( GsonConverterFactory.create())
                .build();

        DataService service = retrofit.create(DataService.class);
        Call<List<Scheduling>> call = service.pegarId();
        call.enqueue(new Callback<List<Scheduling>>() {
            @Override
            public void onResponse(Call<List<Scheduling>> call, Response<List<Scheduling>> response) {
               if(response.isSuccessful()){
                    shedulings = response.body();

                    for (int i = 0; i < shedulings.size(); i++){
                        Scheduling s = shedulings.get( i );
                        playerNames.add(s.getName());
                        System.out.println( "Doctor: " +s.getName() + ", Profile: " + s.getProfile());
                    }
                }
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Slide02Activity.this, android.R.layout.simple_spinner_item, playerNames);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                spDoctor.setAdapter(spinnerArrayAdapter);

                spDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                        nameDoctor = parent.getItemAtPosition(posicao).toString();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        return;
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Scheduling>> call, Throwable t) {
                Log.d("404","Ocorreu um erro: " + t);
            }
        });
        System.out.println( "Hour: " + etHour.getText().toString() );
        etHour.addTextChangedListener(MaskEditUtil.mask(etHour, MaskEditUtil.FORMAT_HOUR));
        System.out.println( "Hour: " + etHour.getText().toString() );

        iValidate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( Slide02Activity.this,"Hora: "+hourSelected +", "+day+"/"+month, Toast.LENGTH_SHORT ).show();
                Toast.makeText( Slide02Activity.this,""+nameDoctor+"/"+nameCategory, Toast.LENGTH_SHORT ).show();
            }
        } );
    }



    /*
    Método abaixo é usado para configurar a data da API Material Calendar View
     */

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
}






