package com.example.emr.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.emr.R;
import com.example.emr.User.Password.PasswordActivity;

public class DetailsAcount extends AppCompatActivity {

    Button btnAlterarSenha, btnAlterarDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.act_details );

        btnAlterarSenha = findViewById( R.id.btnAlterarSenha );
        btnAlterarDados = findViewById( R.id.btnAlterarDados );

        btnAlterarSenha.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getApplicationContext(), PasswordActivity.class ) );
            }
        } );
    }
}
