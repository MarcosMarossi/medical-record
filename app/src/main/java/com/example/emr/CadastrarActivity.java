package com.example.emr;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emr.R;

public class CadastrarActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    private EditText edtNome;
    private EditText edtCPF;
    private EditText edtEmail;
    private EditText edtSenha;

    private Button btnCalendario;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cadastrar);



        edtNome = findViewById(R.id.edtNome);
        edtCPF = findViewById(R.id.edtCPF);
        edtEmail = findViewById(R.id.edtEmail);



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

                if(edtSenha.getText() != null)
                    edtSenha.setText("");

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
}
