package com.example.emr;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.emr.Adapter.AdapterUsr;
import com.example.emr.Avisos.AvisosActivity;
import com.example.emr.Historico.CalendarioActivity;
import com.example.emr.Slide.Sl_01;

public class MenuActivity extends AppCompatActivity {

    ListView lista;

    String[][] dados = {
            {"Agendar", "Escolha o melhor dia para você!"},
            {"Avisos", "Mural de avisos do aplicativo."},
            {"Histórico", "Consulte seus agendamentos."},
            {"Sair", "Deseja sair do aplicativo?"}
    };

    int[] dadosImg = {R.drawable.nurse, R.drawable.report, R.drawable.avisos, R.drawable.arrow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        lista = findViewById(R.id.listMenu);
        lista.setAdapter(new AdapterUsr(this, dados, dadosImg));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        abrirAgendar();
                        break;
                    case 1:
                        abrirAvisos();
                        break;
                    case 2:
                        abrirHistorico();
                        break;
                    case 3:
                        fechar();
                        break;
                    default:
                        Toast.makeText(MenuActivity.this, "dfsf", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void abrirAgendar() {
        Intent intent = new Intent(MenuActivity.this, Sl_01.class);
        startActivity(intent);
    }

    private void abrirHistorico() {
        Intent intent = new Intent(MenuActivity.this, CalendarioActivity.class);
        startActivity(intent);
    }

    private void fechar() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.sair_titulo);
        dialog.setIcon(R.drawable.ic_remove_circle_black_24dp);

        dialog.setPositiveButton(R.string.sair_sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
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

    public void abrirAvisos(){
        Intent intent = new Intent(MenuActivity.this, AvisosActivity.class);
        startActivity(intent);
    }
}
