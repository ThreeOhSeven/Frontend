package edu.purdue.a307.betcha.Helpers;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by kyleohanian on 10/18/17.
 */

public class BToast {
    public static void makeShort(Context context, String message)  {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
