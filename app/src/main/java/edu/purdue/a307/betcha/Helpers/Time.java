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
                long hr = newDiff / 24;
                return String.valueOf(hr) + " hrs";
            }
            if(newDiff < 604800) {
                long days = newDiff / 7;
                return String.valueOf(days) + " days";
            }
            else {
                long days = newDiff / 7;
                long weeks = days / 7;
                return String.valueOf(weeks) + " weeks";
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }return "Oh shit";
    }
}
