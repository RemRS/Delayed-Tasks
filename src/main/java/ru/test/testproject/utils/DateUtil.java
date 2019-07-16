
package ru.test.testproject.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String convertTo8601(Timestamp timestamp) throws ParseException{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        return df.format(Date.from(timestamp.toInstant()));
    }
}
