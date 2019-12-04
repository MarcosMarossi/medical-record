package com.example.emr.User;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.emr.Adapter.ScheduleAdapter;
import com.example.emr.Models.Scheduling;
import com.example.emr.Models.ArraySchedule;
import com.example.emr.R;
import com.example.emr.Services.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class WarningsActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private ArraySchedule arraySchedule;
    private DataService service;
    private RecyclerView recyclerView;
    private List<Scheduling> fotodope = new ArrayList<>(  );
    private ScheduleAdapter scheduleAdapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_warnings);
    }


}
