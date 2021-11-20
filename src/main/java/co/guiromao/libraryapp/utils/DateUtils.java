package co.guiromao.libraryapp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        return dateFormat.format(date);
    }

    public static Date stringToDate(String string) {
        try {
            return new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(string);
        }
        catch(ParseException e) {
            throw new IllegalArgumentException("Illegal argument: " + e);
        }
    }

}
