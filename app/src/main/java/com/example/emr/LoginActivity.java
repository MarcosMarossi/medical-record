package com.example.emr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.emr.User.MenuUsrActivity;

public class LoginActivity extends AppCompatActivity {


    private EditText campoNome;
    private EditText campoSenha;
    private Button btEnviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        campoNome = findViewById(R.id.edtNome);
        campoSenha = findViewById(R.id.edtSenha);

        getSupportActionBar().hide();

    }

    public void fechar(View v){
        finish();
    }

    public void abrirMenuPrincipal(View view){
        Intent intent = new Intent(this, MenuUsrActivity.class);
        startActivity(intent);
    }

    public void abrirRecuperar(View view){
        Intent intent = new Intent(this, RecoverActivity.class);
        startActivity(intent);
    }


}
