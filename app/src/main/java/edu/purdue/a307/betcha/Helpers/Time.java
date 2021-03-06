package edu.purdue.a307.betcha.Helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.purdue.a307.betcha.Activities.PaymentActivity;

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
            if(newDiff < 86400) {
                long hr = newDiff / 60 / 60;
                if((int)hr == 1) {
                    return String.valueOf(hr) + " hr";
                }
                return String.valueOf(hr) + " hrs";
            }
            if(newDiff < 604800) {
                long days = newDiff / 60 / 60 / 24;
                return String.valueOf(days) + " d";
            }
            else {
                long weeks = newDiff / 60 / 60 / 24 / 7;
                return String.valueOf(weeks) + " w";
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }return "Oh shit";
    }
}
