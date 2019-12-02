package com.example.emr.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.emr.R;
import com.example.emr.User.Password.PasswordActivity;

public class DetailsAcount extends AppCompatActivity {

    private Button btnAlterarSenha, btnAlterarDados;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String nome, email, documento;
    private EditText etName, etEmail, etDocument;

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

        etName = findViewById( R.id.etName );
        etEmail = findViewById( R.id.etEmail );
        etDocument = findViewById( R.id.etDocument );

        sharedPreferences = getSharedPreferences("salvarToken", MODE_PRIVATE);
        nome = sharedPreferences.getString("user_name", null);
        email = sharedPreferences.getString("user_email", null);
        documento = sharedPreferences.getString("document", null);

        etName.setText( nome );
        etEmail.setText( email );
        etDocument.setText( documento );
    }
}
