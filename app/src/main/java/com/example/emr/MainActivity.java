package com.example.emr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.emr.Adapter.AdapterCountry;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Spinner idioma;
    private ArrayList<CountryItem> countryList;
    private AdapterCountry adapterCountry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        initList();
        idioma = findViewById(R.id.spinIdioma);

        adapterCountry = new AdapterCountry(this,countryList);
        idioma.setAdapter(adapterCountry);
        /**ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.idioma,android.R.layout.simple_spinner_item);
        idioma.setAdapter(adapter);

        AdapterView.OnItemClickListener escolhaIdioma = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Fazer toda a lógica do idioma assim que estiver tudo pronto
            }
        };**/
        idioma.setSelected(false);
        idioma.setSelection(0,true);

        idioma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CountryItem clickedItem = (CountryItem) parent.getItemAtPosition(position);
                String clickedCountryName = clickedItem.getCountryName();
                Toast.makeText(MainActivity.this, clickedCountryName + " selected", Toast.LENGTH_LONG).show();
                if(clickedCountryName.equals("USA")){
                    idiomaHU();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initList() {
        countryList = new ArrayList<>();
        countryList.add(new CountryItem("PT-BR", R.drawable.brazil));
        countryList.add(new CountryItem("USA", R.drawable.usa3));
        countryList.add(new CountryItem("ES", R.drawable.spain));
        countryList.add(new CountryItem("FR", R.drawable.french));
        countryList.add(new CountryItem("HU", R.drawable.hungary));

    }
    public void abrirTelaLogin(View v){
        startActivity(new Intent(this,LoginActivity.class));
    }
    public void abrirTelaCadastro(View v){
        startActivity(new Intent(this, RefactorActivity.class));
    }

    public void idiomaHU ()
    {
        Locale idioma = new Locale("hu");
        Locale.setDefault(idioma);

        Context context = this;
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());

        config.setLocale(idioma);
        res.updateConfiguration(config, res.getDisplayMetrics());

        SharedPreferences.Editor dados = getSharedPreferences("emr-language", MODE_PRIVATE).edit();
        dados.putString("idioma", "en");
        dados.commit();

        recreate();
    }


}
