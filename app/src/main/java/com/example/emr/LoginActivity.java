package com.example.emr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emr.Models.User;
import com.example.emr.Services.DataService;
import com.example.emr.User.MenuUsrActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {


    private EditText campoNome;
    private EditText campoSenha;
    private Button btEnviar;
    private String email,senha;


    String pegarToken;

    private String stringURL = "https://ey7li2szf0.execute-api.us-east-1.amazonaws.com/dev/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        campoNome = findViewById(R.id.edtNome);
        campoSenha = findViewById(R.id.edtSenha);
        btEnviar = findViewById(R.id.btnEntrar);
        getSupportActionBar().hide();




        btEnviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                email = campoNome.getText().toString();
                senha = campoSenha.getText().toString();

                Toast.makeText(LoginActivity.this, email + senha, Toast.LENGTH_SHORT).show();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(stringURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();




                //User token = new User("admin@admin.com","tESTE123");
                // Pegar o email e senha do usuário
                User token = new User(email,senha);

                DataService service = retrofit.create(DataService.class);
                Call<User> call = service.acessarLogin(token);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(LoginActivity.this, email + senha, Toast.LENGTH_SHORT).show();

                        if(response.isSuccessful()){




                            User user = response.body();
                            pegarToken = user.getToken();
                            user.setToken(pegarToken);

                            if(pegarToken != null){

                                Toast.makeText(LoginActivity.this, email + senha, Toast.LENGTH_SHORT).show();
                                abrirMenuPrincipal();
                                System.out.println(user.getMessage());
                            }





                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });

    }

    public void fechar(View v){
        finish();
    }

    public void abrirMenuPrincipal(){
        Intent intent = new Intent(this, MenuUsrActivity.class);
        startActivity(intent);
    }

    public void abrirRecuperar(View view){
        Intent intent = new Intent(this, RecoverActivity.class);
        startActivity(intent);
    }


}
