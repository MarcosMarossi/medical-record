package com.example.emr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emr.Config.RetrofitConfig;
import com.example.emr.Models.User;
import com.example.emr.Services.DataService;
import com.example.emr.Doctor.MenuDocActivity;
import com.example.emr.Nurse.MenuNurActivity;
import com.example.emr.User.MenuUsrActivity;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private EditText campoNome, campoSenha;
    private Button btEnviar;
    private String email, senha;
    private String getToken, Profile;
    private Retrofit retrofit;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Boolean boolToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        campoNome = findViewById(R.id.edtNome);
        campoSenha = findViewById(R.id.edtSenha);
        btEnviar = findViewById(R.id.btnEntrar);
        getSupportActionBar().hide();

        sharedPreferences = getSharedPreferences("salvarToken", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        try {
            btEnviar.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    email = campoNome.getText().toString();
                    senha = campoSenha.getText().toString();

                    retrofit = RetrofitConfig.retrofitConfig();

                    User token = new User( email, senha );

                    DataService service = retrofit.create( DataService.class );
                    Call<User> POST = service.acessApp( token );

                    POST.enqueue( new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {

                            User user = response.body();
                            getToken = response.body().getToken();

                            editor.putString( "email", email );
                            editor.putString( "pass", senha );
                            editor.putString( "id", user.getId() );
                            editor.putString( "token", getToken );
                            editor.commit();

                            if (user.getToken() != null) {

                                DataService service = retrofit.create( DataService.class );
                                Call<User> GET = service.getToken( getToken );
                                GET.enqueue( new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {
                                        if (response.isSuccessful()) {

                                            Profile = response.body().getProfile();
                                            String name = response.body().getName();
                                            editor.putString( "name", name);
                                            editor.commit();


                                            if (Profile.equals( "medic" )) {
                                                menuMedico();
                                            } else if (Profile.equals( "patient" )) {
                                                menuPaciente();
                                            } else if (Profile.equals( "nurse" )) {
                                                menuEnfermeira();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {
                                        Log.e( "ERROR", "Seu erro ocorreu aqui: " + t + " " + call );
                                    }
                                } );
                            } else {
                                Toast.makeText( LoginActivity.this, "Login ou senha incorretos!", Toast.LENGTH_SHORT ).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.e( "ERROR", "Seu erro ocorreu aqui: " + t + " " + call );
                            Toast.makeText( LoginActivity.this, "Desculpe. Não conseguimos conectar te. E-mail ou senha inválidos.", Toast.LENGTH_SHORT ).show();
                        }
                    } );
                }
            } );

        }   catch (NullPointerException e){
            System.out.println( "Erro:" + e );
        }

        boolToken = sharedPreferences.getBoolean("salvarToken", true);
        getToken = sharedPreferences.getString("token", null);
        if (boolToken == true && getToken != null) {
            campoNome.setText(sharedPreferences.getString("email", null));
            campoSenha.setText(sharedPreferences.getString("pass", null));
        }
    }

    public void fechar(View v) {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

    public void menuPaciente() {
        startActivity(new Intent(this, MenuUsrActivity.class));
    }

    public void menuMedico() {
        startActivity(new Intent(this, MenuDocActivity.class));
    }

    public void menuEnfermeira() {
        startActivity(new Intent(this, MenuNurActivity.class));
    }
}