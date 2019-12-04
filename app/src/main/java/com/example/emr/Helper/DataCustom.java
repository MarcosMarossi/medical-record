package com.example.emr.Helper;

import android.content.Intent;

import java.text.SimpleDateFormat;

public class DataCustom {


    public static String dataCorreta(String d, String m, String a){

        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd/MM/yyyy" );
        String hoje = simpleDateFormat.format( data );

        String returnoData[] = hoje.split( "/" );
        String sday = returnoData[0]; // dia 17
        String smonth = returnoData[1]; // mes 11
        String syear = returnoData[2];

        int dia = Integer.parseInt( d );
        int mes = Integer.parseInt( m );
        int ano = Integer.parseInt( a );

        int diahoje = Integer.parseInt( sday );
        int meshoje = Integer.parseInt( smonth );
        int anohoje = Integer.parseInt( syear );

        if(dia > diahoje && mes >= meshoje && ano >= anohoje){
            return dia + "/" + mes + "/" + ano;
        } else {
            return "0";
        }


    }
}
