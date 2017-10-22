package edu.purdue.a307.betcha.Api;

import android.content.Context;

import edu.purdue.a307.betcha.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kyleohanian on 9/20/17.
 */

public class ApiHelper {
    private static BetchaApi betchaApi;

    public static BetchaApi getInstance(Context context) {
        if (betchaApi == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(/*context.getString(R.string.app_name)*/"http://18.220.176.148:80")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            betchaApi = retrofit.create(BetchaApi.class);
        }

        return betchaApi;
    }
}
