package edu.purdue.a307.betcha.Helpers;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import edu.purdue.a307.betcha.R;

/**
 * Created by kyleohanian on 10/18/17.
 */

public class BToast {

    public static final int GREEN = R.color.iconColor6;
    public static final int RED = R.color.iconColor2;
    public static final int BLUE = R.color.iconColor4;
    public static final int YELLOW = R.color.iconColor7;
    public static final int BLACK = R.color.black;
    public static final int WHITE = R.color.colorIcon;


    public static final int TEXT_DEFAULT = 20;

    public static final int BOTTOM = Gravity.BOTTOM;
    public static final int TOP = Gravity.TOP;

    public static void makeShort(Context context, String message)  {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void makeCustom(Activity context,
                                  String message,
                                  int backgroundColor,
                                  int textColor,
                                  int textSize,
                                  int gravity) {

        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);
        TextView text = (TextView) layout.findViewById(R.id.message);
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[] { 50, 50, 50, 50, 50, 50, 50, 50 });
        shape.setColor(ContextCompat.getColor(context, backgroundColor));
        layout.setBackground(shape);
        text.setTextColor(ContextCompat.getColor(context, textColor));
        text.setText(message);
        text.setTextSize(textSize);

        Toast toast = new Toast(context);
        toast.setGravity(gravity, 0, 50);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();


    }

    public static void makeSuccess(Activity context, String message) {
        makeCustom(context, message, GREEN, WHITE, TEXT_DEFAULT, BOTTOM);
    }

    public static void makeError(Activity context, String message) {
        makeCustom(context, message, RED, WHITE, TEXT_DEFAULT, BOTTOM);
    }

    public static void makeFriendsError(Activity context) {
        makeCustom(context, "Unable to get the list of friends", RED, WHITE, TEXT_DEFAULT, BOTTOM);
    }

    public static void makeBetsError(Activity context) {
        makeCustom(context, "Unable to get the list of bets", RED, WHITE, TEXT_DEFAULT, BOTTOM);
    }

    public static void makeServerError(Activity context) {
        makeCustom(context, "Unable to connect to the betcha! servers", YELLOW, BLACK, TEXT_DEFAULT, BOTTOM);
    }

    public static void makeInformation(Activity context, String message) {
        makeCustom(context, message, BLUE, WHITE, TEXT_DEFAULT, BOTTOM);
    }

}
