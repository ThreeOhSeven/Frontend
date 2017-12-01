package edu.purdue.a307.betcha.Helpers;

/**
 * Created by kyleohanian on 12/1/17.
 */

public class BDate {
    String month;
    String day;
    String year;

    public BDate(String month, String day, String year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
