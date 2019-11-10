package com.example.emr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emr.Models.User;
import com.example.emr.Services.DataService;
import com.example.emr.User.MenuUsrActivity;
import com.example.emr.Doctor.MenuDocActivity;
import com.example.emr.Nurse.MenuNurActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {


    private EditText campoNome, campoSenha;
    private Button btEnviar;
    private String email, senha, stringURL = "https://ey7li2szf0.execute-api.us-east-1.amazonaws.com/dev/";
    String getToken, Profile;
    Retrofit retrofit;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private Boolean boolToken;
    private CheckBox chboxSalvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        campoNome = findViewById(R.id.edtNome);
        campoSenha = findViewById(R.id.edtSenha);
        btEnviar = findViewById(R.id.btnEntrar);
        chboxSalvar = findViewById(R.id.chboxSalvar);
        getSupportActionBar().hide();

        sharedPreferences = getSharedPreferences("salvarToken", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = campoNome.getText().toString();
                senha = campoSenha.getText().toString();

                retrofit = new Retrofit.Builder()
                        .baseUrl(stringURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                User token = new User(email, senha);

                DataService service = retrofit.create(DataService.class);
                Call<User> POST = service.acessarLogin(token);

                POST.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        User user = response.body();

                        if(chboxSalvar.isChecked()){
                            editor.putString("email",email);
                            editor.putString("pass",senha);
                            editor.putString("token",user.getToken());
                            editor.commit();
                        }

                        if (user.getToken() != null) {

                            DataService service = retrofit.create(DataService.class);
                            Call<User> GET = service.pegarToken(user.getToken());
                            GET.enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    if (response.isSuccessful()) {

                                        Profile = response.body().getProfile();
                                        if (Profile.equals("medic")) {
                                            menuMedico();
                                        } else if (Profile.equals("patient")) {
                                            menuPaciente();
                                        } else if (Profile.equals("nurse")) {
                                            menuEnfermeira();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    Log.e("ERROR", "Seu erro ocorreu aqui: " + t + " " + call);
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });

        boolToken = sharedPreferences.getBoolean("salvarToken", true);
        getToken = sharedPreferences.getString("token", null);
        if (boolToken == true && getToken != null) {
            campoNome.setText(sharedPreferences.getString("email", null));
            campoSenha.setText(sharedPreferences.getString("pass", null));
        }
    }

    public void fechar(View v) {
        finish();
    }

    public void menuPaciente() {
        Intent intent = new Intent(this, MenuUsrActivity.class);
        startActivity(intent);
    }

    public void menuMedico() {
        Intent intent = new Intent(this, MenuDocActivity.class);
        startActivity(intent);
    }

    public void menuEnfermeira() {
        Intent intent = new Intent(this, MenuNurActivity.class);
        startActivity(intent);
    }

    public void abrirRecuperar(View view) {
        Intent intent = new Intent(this, RecoverActivity.class);
        startActivity(intent);
    }
}
