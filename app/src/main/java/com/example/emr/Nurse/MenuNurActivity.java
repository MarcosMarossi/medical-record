package com.example.emr.Nurse;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.emr.Adapter.Adaptador;
import com.example.emr.Doctor.MenuDocActivity;
import com.example.emr.LoginActivity;
import com.example.emr.Models.User;
import com.example.emr.R;

public class MenuNurActivity extends AppCompatActivity {

    ListView lista;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String[][] dados = {
            {"Monitoramento", "Monitore internações."},
            {"Validação", "Validações das enfermeiras."},
            {"Sair", "Deseja sair do aplicativo?"}
    };

    int[] dadosImg = {R.drawable.monitora, R.drawable.validar,R.drawable.arrow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_menu_nur);

        sharedPreferences = getSharedPreferences("salvarToken", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        lista = findViewById(R.id.listNurse);
        lista.setAdapter(new Adaptador(this, dados, dadosImg));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        monitoramento();
                        break;
                    case 1:
                        verValidacao();
                        break;
                    case 2:
                        fechar();
                        break;
                    default:
                        Toast.makeText(MenuNurActivity.this, "Não conseguimos encontrar a melhor opção", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void monitoramento() {
        Intent intent = new Intent(MenuNurActivity.this,MonitoringActivity.class);
        startActivity(intent);

    }

    public void verValidacao(){
        Intent intent = new Intent(MenuNurActivity.this,ValidationActivity.class);
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
                Intent intent = new Intent(MenuNurActivity.this, LoginActivity.class);
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
