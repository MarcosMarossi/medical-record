package com.example.emr.Doctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.emr.Adapter.Adaptador;
import com.example.emr.LoginActivity;
import com.example.emr.Models.User;
import com.example.emr.R;
import com.example.emr.User.MenuUsrActivity;

public class MenuDocActivity extends AppCompatActivity {

    ListView lista;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor  editor;

    String[][] dados = {
            {"Consultas", "Veja quem serão seus pacientes!"},
            {"Criar Avisos", "Digite avisos aos pacientes."},
            {"Monitoramento", "Monitore internações."},
            {"Validação", "Validações das enfermeiras."},
            {"Sair", "Deseja sair do aplicativo?"}
    };

    int[] dadosImg = {R.drawable.consultas, R.drawable.report, R.drawable.monitora, R.drawable.validar,R.drawable.arrow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_menu_doc);

        sharedPreferences = getSharedPreferences("salvarToken", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        lista = findViewById(R.id.listDoctor);
        lista.setAdapter(new Adaptador(this, dados, dadosImg));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        abrirConsultas();
                        break;
                    case 1:
                        criarAvisos();
                        break;
                    case 2:
                        monitoramento();
                        break;
                    case 3:
                        verValidacao();
                        break;
                    case 4:
                        fechar();
                        break;
                    default:
                        Toast.makeText(MenuDocActivity.this, "Não conseguimos encontrar a melhor opção", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void abrirConsultas() {
        Intent intent = new Intent(MenuDocActivity.this, QueryActivity.class);
        startActivity(intent);
    }

    private void criarAvisos() {
        Intent intent = new Intent(MenuDocActivity.this,WarningsActivity.class);
        startActivity(intent);
    }

    private void monitoramento() {
        Intent intent = new Intent(MenuDocActivity.this,MonitoringActivity.class);
        startActivity(intent);

    }

    public void verValidacao(){
        Intent intent = new Intent(MenuDocActivity.this,ValidationActivity.class);
        startActivity(intent);

    }

    public void fechar(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.sair_titulo);
        dialog.setIcon(R.drawable.ic_remove_circle_black_24dp);

        dialog.setPositiveButton(R.string.sair_sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                User user = new User();
                user.setToken("");
                editor.putString("token",user.getToken());
                editor.commit();
                Intent intent = new Intent(MenuDocActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        dialog.setNegativeButton(R.string.sair_nao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.create();
        dialog.show();

    }
}
