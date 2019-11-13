package com.example.emr;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emr.Models.User;
import com.example.emr.Services.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RefactorActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    private EditText edtNome;
    private EditText edtCPF;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnCadastrar;
    private String tk, email, password, cpf, name, stringURL = "https://ey7li2szf0.execute-api.us-east-1.amazonaws.com/dev/";
    Retrofit retrofit;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_register);

        edtNome = findViewById(R.id.edtNome);
        edtCPF = findViewById(R.id.edtCPF);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);



        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retrofit = new Retrofit.Builder()
                        .baseUrl(stringURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                name = edtNome.getText().toString();
                cpf = edtCPF.getText().toString();
                email = edtEmail.getText().toString();
                password = edtPassword.getText().toString();

                User register = new User(name,cpf,email,password);

                DataService service = retrofit.create(DataService.class);
                Call<User> POST = service.registrarUsuario(register);

                POST.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User register = response.body();
                        tk = register.getToken();
                        Toast.makeText(RefactorActivity.this, tk, Toast.LENGTH_SHORT).show();
                        login();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });




            }
        });



    }

    public void limparCampos(View v){

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Exclusão de todos os seus dados");
        dialog.setMessage("Você tem certeza que deseja excluir?");

        dialog.setIcon(android.R.drawable.ic_notification_clear_all);

        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(edtNome.getText() != null)
                edtNome.setText("");

                if(edtEmail.getText() != null)
                    edtEmail.setText("");

                if(edtPassword.getText() != null)
                    edtPassword.setText("");

                if(edtCPF.getText() != null)
                    edtCPF.setText("");

            }
        });

        dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Textos mantidos!", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.create();
        dialog.show();

    }

    public void login(){
        Intent it = new Intent(RefactorActivity.this, LoginActivity.class);
        startActivity(it);
    }
}
