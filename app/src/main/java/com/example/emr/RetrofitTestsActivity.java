package com.example.emr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.emr.Configuracoes.RetrofitConfig;
import com.example.emr.Models.CID;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitTestsActivity extends AppCompatActivity {

    private EditText edtCid;
    private Button btnEnviar;
    private TextView txtResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_tests);

        edtCid = findViewById(R.id.edtCid);
        txtResponse = findViewById(R.id.txtResponse);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<List<CID>> call = new RetrofitConfig().getCIDService().buscarCid(edtCid.getText().toString().toUpperCase());
                call.enqueue(new Callback<List<CID>>() {

                    @Override
                    public void onResponse(Call<List<CID>> call, Response<List<CID>> response) {
                        List<CID> cid = response.body();
                        txtResponse.setText(cid.toString());
                    }

                    @Override
                    public void onFailure(Call<List<CID>> call, Throwable t) {
                        Log.e("CIDService   ", "Erro ao buscar o cid:" + t.getMessage());
                    }
                });

            }
        });

    }
}
