package edu.purdue.a307.betcha.Api;

import android.content.Context;

import edu.purdue.a307.betcha.R;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kyleohanian on 9/20/17.
 */

public class ApiHelper {
    private static BetchaApi betchaApi;

    public static BetchaApi getInstance(Context context) {
        if (betchaApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(/*context.getString(R.string.app_name)*/"http://52.14.139.38:80")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            betchaApi = retrofit.create(BetchaApi.class);
        }

        return betchaApi;
    }
}
