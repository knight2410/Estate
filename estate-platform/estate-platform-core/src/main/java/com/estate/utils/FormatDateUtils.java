package com.estate.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDateUtils {

    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        return strDate;
    }
}
