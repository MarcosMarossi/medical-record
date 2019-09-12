package com.example.emr.Slide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

import com.example.emr.MenuActivity;
import com.example.emr.R;

public class Agendar extends AppCompatActivity {

    Spinner hospitais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_agendar_info);

        getSupportActionBar().hide();
/*
        hospitais = findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.consultorios, android.R.layout.simple_spinner_item);
        hospitais.setAdapter(adapter);
*/
    }


    public void abrirCalendario(View view){
        Intent cal = new Intent(Agendar.this, Calendar.class);
        startActivity(cal);
    }

    public void voltarMenu(View v){
        Intent intent = new Intent(Agendar.this, MenuActivity.class);
        startActivity(intent);
    }
}
