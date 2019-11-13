package com.example.emr.User.Slide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.emr.Models.Date;
import com.example.emr.User.MenuUsrActivity;
import com.example.emr.R;

public class SlideUltimate extends AppCompatActivity {

    Spinner hospitais,medicos;
    ImageView validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_slide03);

        getSupportActionBar().hide();
        hospitais = findViewById(R.id.spnCategoria);
        ArrayAdapter adapterCat = ArrayAdapter.createFromResource(this,R.array.categorias, android.R.layout.simple_spinner_dropdown_item);
        hospitais.setAdapter(adapterCat);

        medicos = findViewById(R.id.spnCategoria2);
        ArrayAdapter adapterDoc = ArrayAdapter.createFromResource(this,R.array.medicos, android.R.layout.simple_spinner_dropdown_item);
        medicos.setAdapter(adapterDoc);

        validar = findViewById(R.id.imgValidar);

        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date data = new Date();
                Toast.makeText(SlideUltimate.this, data.getMonth() + data.getYear(), Toast.LENGTH_SHORT).show();

            }
        });




    }

    public void voltar(View v){
        Intent intent = new Intent(SlideUltimate.this, SlideSecond.class);
        startActivity(intent);
    }

    public void validar(View v){
        Intent intent = new Intent(SlideUltimate.this, MenuUsrActivity.class);
        startActivity(intent);
    }
}
