package com.alkemy.desafioDisney.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static Date stringToDate(String fetchString) throws ParseException {
        SimpleDateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy");
        return fechaFormat.parse(fetchString);
    }

    public static String dateToString(Date fetch){
        SimpleDateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fetchString = fechaFormat.format(fetch);
        return fetchString;
    }
}
