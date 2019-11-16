package com.example.emr.User.Scheduling;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.emr.Models.Sheduling;
import com.example.emr.Models.User;
import com.example.emr.R;
import com.example.emr.Services.DataService;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Slide02Activity<dia> extends AppCompatActivity {

    private ImageView iValidate, iBack;
    private MaterialCalendarView calendarioAgendar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private Spinner spCategory, spDoctor;
    private String stringURL = "https://ey7li2szf0.execute-api.us-east-1.amazonaws.com/dev/";
    private List<Sheduling> shedulings = new ArrayList<>(  );
    private ArrayList<String> playerNames = new ArrayList<String>();
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.s_slide02 );
        getSupportActionBar().hide();

        iValidate = findViewById( R.id.iShedule );
        iBack = findViewById( R.id.iClose );

        sharedPreferences = getSharedPreferences("salvarData", MODE_PRIVATE);
        editor = sharedPreferences.edit();

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


                editor.putString("dia", String.valueOf( date.getDay() ));
                editor.putString("mes",  String.valueOf(date.getMonth() ));
                editor.putString("ano",  String.valueOf(date.getYear() ));
                editor.commit();


            }
        });
        /*
        spCategory = findViewById( R.id.spCategory );
        ArrayAdapter<CharSequence> adapterCatorgory = ArrayAdapter.createFromResource(this,
                R.array.categorias, android.R.layout.simple_spinner_item);
        adapterCatorgory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapterCatorgory);

        spDoctor = findViewById(R.id.spDoctor);
        ArrayAdapter<CharSequence> adapterDoctor = ArrayAdapter.createFromResource(this,
                R.array.medicos, android.R.layout.simple_spinner_item);
        adapterDoctor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDoctor.setAdapter(adapterDoctor);*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(stringURL)
                .addConverterFactory( GsonConverterFactory.create())
                .build();

        DataService service = retrofit.create(DataService.class);

        Call<List<Sheduling>> call = service.pegarId();

        call.enqueue(new Callback<List<Sheduling>>() {
            @Override
            public void onResponse(Call<List<Sheduling>> call, Response<List<Sheduling>> response) {
                //System.out.println( shedulings.get(0).getEmail() );

                if(response.isSuccessful()){

                    shedulings = response.body();

                    for (int i = 0; i < shedulings.size(); i++){
                        Sheduling s = shedulings.get( i );
                        playerNames.add(s.getName());
                        System.out.println( "Medicos: " +s.getName() + s.getProfile());
                    }

                }

                spDoctor=findViewById( R.id.spDoctor );

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Slide02Activity.this, android.R.layout.simple_spinner_item, playerNames);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                spDoctor.setAdapter(spinnerArrayAdapter);

            }

            @Override
            public void onFailure(Call<List<Sheduling>> call, Throwable t) {

            }
        });
    }


}





