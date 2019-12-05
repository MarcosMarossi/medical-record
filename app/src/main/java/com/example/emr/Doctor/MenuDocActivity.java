package com.example.emr.Doctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.emr.Adapter.MenuAdapter;
import com.example.emr.LoginActivity;
import com.example.emr.Models.User;
import com.example.emr.R;
import com.example.emr.User.RecyclerItemClickListener;

public class MenuDocActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor  editor;
    private MenuAdapter menuAdapter;

    int[][] dados = {
            {R.string.tit_consulta, R.string.desc_consultas},
            {R.string.tit_monitoramento, R.string.desc_monitoramento},
            {R.string.historico_bpm,R.string.desc_bpm},
            {R.string.tit_sair, R.string.desc_sair}
    };

    int[] dadosImg = {R.drawable.consultas, R.drawable.monitora, R.drawable.log_user,R.drawable.arrow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.act_menu_doc );

        sharedPreferences = getSharedPreferences( "salvarToken", MODE_PRIVATE );
        editor = sharedPreferences.edit();

        recyclerView = findViewById( R.id.recyclerView );
        menuAdapter = new MenuAdapter( getApplication(), dados, dadosImg );
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        recyclerView.setAdapter( menuAdapter );

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                switch (position) {
                                    case 0:
                                        abrirConsultas();
                                        break;
                                    case 1:
                                        monitoramento();
                                        break;
                                    case 2:
                                        consultarBPM();
                                        break;
                                    case 3:
                                        fechar();
                                        break;
                                    default:
                                        Toast.makeText( MenuDocActivity.this, "Não conseguimos encontrar a melhor opção", Toast.LENGTH_SHORT ).show();
                                }
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
    }

        private void abrirConsultas(){
        Intent intent = new Intent(MenuDocActivity.this, QueryActivity.class);
        startActivity(intent);
    }

    private void monitoramento() {
        Intent intent = new Intent(MenuDocActivity.this,MonitoringActivity.class);
        startActivity(intent);

    }

    public void consultarBPM(){
        Intent intent = new Intent(MenuDocActivity.this,Act_Patient_BPM.class);
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

