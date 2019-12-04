package com.example.emr.User;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emr.Adapter.MenuAdapter;
import com.example.emr.LoginActivity;
import com.example.emr.Models.User;
import com.example.emr.R;
import com.example.emr.User.Scheduling.Slide01Activity;

import static com.example.emr.R.*;

public class MenuUsrActivity extends AppCompatActivity {


    private ListView lista;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String name;
    private TextView txtWelcome;
    private MenuAdapter menuAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.act_menu_usr );
        //getSupportActionBar().hide();

        sharedPreferences = getSharedPreferences("salvarToken", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //name = sharedPreferences.getString("name", null);
        //txtWelcome = findViewById( id.txtWelcome );

        int[][] dados = {
                {string.tit_agendar, string.desc_agendar},
                {string.tit_account, string.desc_account},
                {string.tit_historico, string.desc_historico},
                {string.tit_bpm,string.historico_bpm},
                {string.tit_sair, string.desc_sair}};

        int[] dadosImg = {drawable.nurse, R.drawable.boy ,drawable.report,drawable.monitoring ,drawable.arrow};

        recyclerView = findViewById( id.recyclerView );
        menuAdapter = new MenuAdapter(getApplication(),dados,dadosImg);
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                                        startActivity(new Intent(getApplicationContext(), Slide01Activity.class));
                                        break;
                                    case 1:
                                        startActivity(new Intent(getApplicationContext(), DetailsAcount.class));
                                        break;
                                    case 2:
                                        startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                                        break;
                                    case 3:
                                        startActivity(new Intent(getApplicationContext(),LogActivity.class));
                                        break;
                                    case 4:
                                        fechar();
                                        break;
                                    default:
                                        Toast.makeText(MenuUsrActivity.this, "Não foi possível", Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.toolbar,menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case id.MenuSair:
                fechar();
                break;
        }
        return super.onOptionsItemSelected( item );
    }
}