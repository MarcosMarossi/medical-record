package com.example.emr.User.Slide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

import com.example.emr.User.MenuUsrActivity;
import com.example.emr.R;

public class SlideInitial extends AppCompatActivity {

    Spinner hospitais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_slide02);

        getSupportActionBar().hide();
/*
        hospitais = findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.consultorios, android.R.layout.simple_spinner_item);
        hospitais.setAdapter(adapter);
*/
    }


    public void abrirCalendario(View view){
        Intent cal = new Intent(SlideInitial.this, SlideSecond.class);
        startActivity(cal);
    }

    public void voltarMenu(View v){
        Intent intent = new Intent(SlideInitial.this, MenuUsrActivity.class);
        startActivity(intent);
    }
}
