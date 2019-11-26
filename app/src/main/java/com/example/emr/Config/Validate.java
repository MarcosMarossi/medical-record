package com.example.emr.Config;

public class Validate {

    public static String validateHour(String hour){
        String returnoData[] = hour.split( "/" );
        int dia = Integer.parseInt( returnoData[0]  );
        int mes = Integer.parseInt( returnoData[1]  );
        int ano = Integer.parseInt( returnoData[2]  );

        if(dia > 0 && dia < 31 && (mes == 02 && dia < 29)){

        }
    return "";

    }

    public static String validateDate(String date){
        return "";

    }
}
