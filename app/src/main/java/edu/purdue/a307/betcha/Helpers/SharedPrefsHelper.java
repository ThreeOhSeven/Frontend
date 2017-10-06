package edu.purdue.a307.betcha.Helpers;
import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefsHelper {
    public static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences("edu.purdue.a307.betcha", Context.MODE_PRIVATE);
    }

    public static void setSelfToken(Context context, String selfTok) {
        SharedPrefsHelper.getSharedPrefs(
                context).edit().putString("selfToken",selfTok);
    }

    public static String getSelfToken(Context context) {
        return SharedPrefsHelper.getSharedPrefs(context).getString("selfToken","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoia3lsZS5vaGFuaWFuQGdtYWlsLmNvbSIsImV4cCI6MTUwNzI3OTUyNH0.eaEJiqqD9AEc8-Hf6gXhPVhL2V0tWJ8I0ZKWQMzAS8Q");
    }
}