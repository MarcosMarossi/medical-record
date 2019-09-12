package com.example.emr.Slide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.emr.MenuActivity;
import com.example.emr.R;

public class Ultimo extends AppCompatActivity {

    Spinner hospitais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_agendar_valida);

        getSupportActionBar().hide();

        hospitais = findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.consultorios, android.R.layout.simple_spinner_item);
        hospitais.setAdapter(adapter);

        System.out.println(adapter.getAutofillOptions());

    }

    public void voltarMenu(View v){
        Intent intent = new Intent(Ultimo.this, MenuActivity.class);
        startActivity(intent);
    }
}
