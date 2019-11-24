package com.example.emr.Helper;

import android.content.Intent;

public class DataCustom {

    public static String horaMinuto(String horario){
        String returnoData[] = horario.split( ":" );
        String hora = returnoData[0]; // dia 17
        String minuto = returnoData[1]; // mes 11

        int hr = Integer.parseInt(hora);
        System.out.println( "oi?" + hr );

            if (hr >= 7 && hr < 17){
                System.out.println( "oi?" );
                return "0";
            } else {
                String horaMinuto = hora + ":" + minuto;
                return horaMinuto;
            }
    }
}
