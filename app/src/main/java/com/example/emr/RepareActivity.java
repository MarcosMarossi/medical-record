package com.example.emr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emr.Config.RetrofitConfig;
import com.example.emr.Models.User;
import com.example.emr.Services.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RepareActivity extends AppCompatActivity {

    private Button btReparar;
    private EditText etRecuperar;
    private Retrofit retrofit;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.act_repare );

        getSupportActionBar().hide();

        btReparar = findViewById( R.id.btRecuperar );
        etRecuperar = findViewById( R.id.etRecuperar );

        btReparar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retrofit = RetrofitConfig.retrofitConfig();

                String email = etRecuperar.getText().toString();

                User oEmail = new User( email );

                DataService service = retrofit.create( DataService.class );
                Call<User> call = service.resetEmail( oEmail );

                call.enqueue( new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()){
                            Toast.makeText( RepareActivity.this, "Email enviado com sucesso!", Toast.LENGTH_SHORT ).show();
                        } else {
                            Toast.makeText( RepareActivity.this, "Email não existente!", Toast.LENGTH_SHORT ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        System.out.println( t );
                    }
                } );

            }
        } );
    }
}
