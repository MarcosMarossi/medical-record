package com.example.emr.User.Password;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.emr.R;

public class PasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.act_password );

        getWindow().setStatusBarColor( Color.parseColor( "#4CAF50" ));
    }
}
