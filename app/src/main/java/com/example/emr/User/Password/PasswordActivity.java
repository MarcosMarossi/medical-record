package com.example.emr.User.Password;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emr.Config.RetrofitConfig;
import com.example.emr.Models.User;
import com.example.emr.R;
import com.example.emr.Services.DataService;
import com.example.emr.User.HistoryActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PasswordActivity extends AppCompatActivity {

    private EditText etOldPassword, etNewPassword;
    private Button btAlterar, btnSair;
    private Retrofit retrofit;
    private SharedPreferences sharedPreferences;
    private String email, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.act_password );

        getWindow().setStatusBarColor( Color.parseColor( "#4CAF50" ));
        getSupportActionBar().hide();

        sharedPreferences = getSharedPreferences("salvarToken", MODE_PRIVATE);



        etOldPassword = findViewById( R.id.etOldPassword );
        etNewPassword = findViewById( R.id.etNewPassword );
        btAlterar = findViewById( R.id.btnAlterarSenha );

        btnSair.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getApplicationContext(), HistoryActivity.class ) );
            }
        } );


    }

    public void alterar(View view){
        email = sharedPreferences.getString("email", null);
        senha = sharedPreferences.getString("pass", null);
        String OldPassword = etOldPassword.getText().toString();
        String NewPassword = etNewPassword.getText().toString();

        User change = new User( email, senha, NewPassword, OldPassword,0);

        retrofit = RetrofitConfig.retrofitConfig();
        DataService service = retrofit.create( DataService.class );
        Call<User> call = service.resetPassword( change );

        call.enqueue( new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText( PasswordActivity.this, "Senha alterada.", Toast.LENGTH_SHORT ).show();

                } else{
                    Toast.makeText( PasswordActivity.this, "Senhas não correspondem!", Toast.LENGTH_SHORT ).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        } );
    }
}
