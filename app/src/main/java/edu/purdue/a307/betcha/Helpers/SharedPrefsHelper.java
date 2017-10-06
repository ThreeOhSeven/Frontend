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
        return SharedPrefsHelper.getSharedPrefs(context).getString("selfToken","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDcyODA3MzYsInVzZXIiOiJreWxlLm9oYW5pYW5AZ21haWwuY29tIn0.w6_Og050TbkfE5XEJUnFV2beJ6zQQTNeznV48QIonU4");
    }
}