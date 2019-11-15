package com.example.emr.User.Scheduling;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;

import com.example.emr.LoginActivity;
import com.example.emr.Models.User;
import com.example.emr.R;
import com.example.emr.User.MenuUsrActivity;

public class Slide01Activity extends AppCompatActivity {

    private ImageView iNext, iBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.s_slide01 );

        getSupportActionBar().hide();

        iNext = findViewById( R.id.iNext );
        iBack = findViewById( R.id.iBackMenu );

        iNext.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Slide01Activity.this, Slide02Activity.class);
                startActivity(it);
            }
        } );

        iBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(Slide01Activity.this);
                dialog.setTitle( R.string.sair_titulo);
                dialog.setIcon( R.drawable.ic_remove_circle_black_24dp);

                dialog.setPositiveButton( R.string.sair_sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent it = new Intent(Slide01Activity.this, MenuUsrActivity.class);
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
