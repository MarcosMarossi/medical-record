package com.example.emr.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.example.emr.Adapter.ScheduleAdapter;
import com.example.emr.Config.RetrofitConfig;
import com.example.emr.Models.Scheduling;
import com.example.emr.Models.Test;
import com.example.emr.R;
import com.example.emr.Services.DataService;
import com.example.emr.User.Scheduling.RecordUserActivity;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WarningsActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private Test test;
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
