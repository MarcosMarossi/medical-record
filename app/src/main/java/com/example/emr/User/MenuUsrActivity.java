package com.example.emr.User;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.emr.Adapter.Adaptador;
import com.example.emr.Doctor.MenuDocActivity;
import com.example.emr.LoginActivity;
import com.example.emr.Models.User;
import com.example.emr.R;
import com.example.emr.User.Slide.SlideInitial;

import static com.example.emr.R.*;

public class MenuUsrActivity extends AppCompatActivity {

    ListView lista;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    int[][] dados = {
            {string.tit_agendar, string.desc_agendar},
            {string.tit_avisos, string.desc_avisos},
            {string.tit_historico, string.desc_historico},
            {string.tit_sair, string.desc_sair}

    };

    int[] dadosImg = {drawable.nurse, drawable.report, drawable.avisos, drawable.arrow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.act_menu_usr);


        sharedPreferences = getSharedPreferences("salvarToken", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        lista = findViewById(id.listUser);
        lista.setAdapter(new Adaptador(this, dados, dadosImg));

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
                        Toast.makeText(MenuUsrActivity.this, "dfsf", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void abrirAgendar() {
        Intent intent = new Intent(MenuUsrActivity.this, SlideInitial.class);
        startActivity(intent);
    }

    private void abrirHistorico() {
        Intent intent = new Intent(MenuUsrActivity.this, CalendarActivity.class);
        startActivity(intent);
    }

    private void fechar() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(string.sair_titulo);
        dialog.setIcon(drawable.ic_remove_circle_black_24dp);

        dialog.setPositiveButton(string.sair_sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                User user = new User();
                user.setToken("");
                editor.putString("token",user.getToken());
                editor.commit();
                Intent intent = new Intent(MenuUsrActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        dialog.setNegativeButton(string.sair_nao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.create();
        dialog.show();

    }

    public void abrirAvisos(){
        Intent intent = new Intent(MenuUsrActivity.this, WarningsActivity.class);
        startActivity(intent);
    }
}
