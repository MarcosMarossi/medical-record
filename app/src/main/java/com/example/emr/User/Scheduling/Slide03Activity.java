package com.example.emr.User.Scheduling;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.emr.R;
import com.example.emr.User.MenuUsrActivity;

public class Slide03Activity extends AppCompatActivity {

    private ImageView iShedule, iBack, iClose;
    private Spinner spnCategory, spnDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.s_slide03 );

        getSupportActionBar().hide();

        iShedule = findViewById( R.id.iShedule );
        iBack = findViewById( R.id.iBack );
        iClose = findViewById( R.id.iClose );

        spnCategory = findViewById(R.id.spCategory);
        ArrayAdapter<CharSequence> adapterCatorgory = ArrayAdapter.createFromResource(this,
                R.array.categorias, android.R.layout.simple_spinner_item);
        adapterCatorgory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategory.setAdapter(adapterCatorgory);

        spnDoctor = findViewById(R.id.spDoctor);
        ArrayAdapter<CharSequence> adapterDoctor = ArrayAdapter.createFromResource(this,
                R.array.medicos, android.R.layout.simple_spinner_item);
        adapterDoctor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDoctor.setAdapter(adapterDoctor);

        iShedule.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        } );

        iBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Slide03Activity.this, Slide02Activity.class);
                startActivity(it);

            }
        } );

        iClose.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Slide03Activity.this);
                dialog.setTitle( R.string.sair_titulo);
                dialog.setIcon( R.drawable.ic_remove_circle_black_24dp);

                dialog.setPositiveButton( R.string.sair_sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent it = new Intent(Slide03Activity.this, MenuUsrActivity.class);
                        startActivity(it);
                    }
                });

                dialog.setNegativeButton( R.string.sair_nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.create();
                dialog.show();
            }
        } );


    }
}
