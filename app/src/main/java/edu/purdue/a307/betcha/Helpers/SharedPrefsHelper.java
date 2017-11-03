package edu.purdue.a307.betcha.Helpers;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import edu.purdue.a307.betcha.Models.AccountInformation;
import edu.purdue.a307.betcha.Models.User;


public class SharedPrefsHelper {
    public static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences("edu.purdue.a307.betcha", Context.MODE_PRIVATE);
    }

    public static void setSelfToken(Context context, String selfTok) {
        SharedPrefsHelper.getSharedPrefs(
                context).edit().putString("selfToken",selfTok).apply();
    }

    public static String getSelfToken(Context context) {
        return SharedPrefsHelper.getSharedPrefs(context).getString("selfToken","");
    }

    public static void setAccountInformation(Context context, User accountInformation) {
        String json = new Gson().toJson(accountInformation);
        SharedPrefsHelper.getSharedPrefs(
                context).edit().putString("accountInformation",json).apply();
    }

    public static User getAccountInformation(Context context) {
        String prefs = SharedPrefsHelper.getSharedPrefs(context).getString("accountInformation","");
        User accountInformation = new Gson().fromJson(prefs, User.class);
        return accountInformation;
    }

    public static String getUserID(Context context) {
        User accountInformation = getAccountInformation(context);
        return accountInformation.getId();
    }
}