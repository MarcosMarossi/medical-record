package com.example.emr.Nurse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.emr.Adapter.AdapterDoctor;
import com.example.emr.Adapter.AdapterNurse;
import com.example.emr.Doctor.MenuDocActivity;
import com.example.emr.R;

public class MenuNurActivity extends AppCompatActivity {

    ListView lista;

    String[][] dados = {
            {"Consultas", "Veja quem serão seus pacientes!"},
            {"Criar Avisos", "Local para escrever avisos aos pacientes."},
            {"Monitoramento", "Monitore seus pacientes internados."},
            {"Validação", "Últimas validações das enfermeiras."},
            {"Sair", "Deseja sair do aplicativo?"}
    };

    int[] dadosImg = {R.drawable.nurse, R.drawable.report, R.drawable.avisos, R.drawable.arrow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_menu_nur);

        lista = findViewById(R.id.listDoctor);
        lista.setAdapter(new AdapterNurse(this, dados, dadosImg));

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
                        Toast.makeText(MenuNurActivity.this, "Não conseguimos encontrar a melhor opção", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void abrirConsultas() {

    }

    private void criarAvisos() {

    }

    private void monitoramento() {

    }

    public void verValidacao(){

    }

    public void fechar(){

    }
}
