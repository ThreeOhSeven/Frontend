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
<<<<<<< HEAD
                    .baseUrl(/*context.getString(R.string.app_name)*/"http://18.220.176.148:80")
=======
                    .baseUrl(/*context.getString(R.string.app_name)*/"http://18.220.176.148")
>>>>>>> e69d76eb1d706336244ac47c6d43beab69a77015
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            betchaApi = retrofit.create(BetchaApi.class);
        }

        return betchaApi;
    }
}
