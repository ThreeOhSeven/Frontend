package edu.purdue.a307.betcha.Helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kyleohanian on 11/29/17.
 */

public class Time {

    public static String getTimeDifference(String betDate) {
        SimpleDateFormat parser = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        try {
            Date bet = parser.parse(betDate);
            Date currentDate = new Date();
            long diff = currentDate.getTime() - bet.getTime();
            long newDiff = diff / 1000;
            if(newDiff < 60) {
                return "Just Now";
            }

            if(newDiff < 3600) {
                long min = newDiff / 60;
                return String.valueOf(min) + " min";
            }
            else {
                return "und";
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }return "Oh shit";
    }
}
