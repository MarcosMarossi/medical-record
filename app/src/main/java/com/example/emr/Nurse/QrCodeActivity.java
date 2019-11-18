package com.example.emr.Nurse;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emr.Models.Heartbeat;
import com.example.emr.R;
import com.example.emr.Services.DataService;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QrCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    Retrofit retrofit;
    private String stringURL = "https://ltc7q76qp5.execute-api.us-east-1.amazonaws.com/dev/heartbeat/";
    private ZXingScannerView scannerView;
    private TextView txtResult;
    private Call<List<Heartbeat>> call;
    private  DataService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        retrofit = new Retrofit.Builder()
                .baseUrl(stringURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         service = retrofit.create(DataService.class);


        scannerView = (ZXingScannerView)findViewById(R.id.zxscan);
        txtResult = (TextView)findViewById(R.id.txt_result);

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        scannerView.setResultHandler(QrCodeActivity.this);
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(QrCodeActivity.this,"You must accept the permission",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();
    }


    @Override
    protected void onDestroy() {
        scannerView.stopCamera();
        super.onDestroy();
    }

    @Override
    public void handleResult(Result rawResult) {

        //txtResult.setText(rawResult.toString());
        String mac = rawResult.toString();
        mac = mac.substring(mac.lastIndexOf("/")+1);
        txtResult.setText(mac);
        startActivity(new Intent(QrCodeActivity.this,ActivityListPatient.class).putExtra("mac",mac));
        //call = service.getBPM(mac);


        /**call = service.getBPM();
        call.enqueue(new Callback<Heartbeat>() {
            @Override
            public void onResponse(Call<Heartbeat> call, Response<Heartbeat> response) {
                Heartbeat heartbeat = response.body();
                txtResult.setText(heartbeat.getHeartBeat());
            }

            @Override
            public void onFailure(Call<Heartbeat> call, Throwable t) {
                Toast.makeText(QrCodeActivity.this,"Deu ruim",Toast.LENGTH_LONG).show();
            }
        });**/


    }
}
