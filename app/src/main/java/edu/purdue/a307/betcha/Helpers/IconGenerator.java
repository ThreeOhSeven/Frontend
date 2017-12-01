package edu.purdue.a307.betcha.Helpers;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import edu.purdue.a307.betcha.R;

/**
 * Created by kyleohanian on 10/5/17.
 */

public class IconGenerator {

    private static int[] icons = {R.drawable.icon_ac_24dp,
                R.drawable.icon_build_24dp, R.drawable.icon_cake_24dp, R.drawable.icon_bell_24dp,
                R.drawable.icon_car_24dp, R.drawable.icon_cloud_24dp, R.drawable.icon_computer_24dp,
                R.drawable.icon_flight_24dp, R.drawable.icon_spa_24dp, R.drawable.icon_sun_24dp};
    private static int[] colors = {R.color.iconColor1,
                R.color.iconColor2, R.color.iconColor3, R.color.iconColor4,
                R.color.iconColor5, R.color.iconColor6, R.color.iconColor7,
                R.color.iconColor8, R.color.iconColor9, R.color.iconColor10};

    public static void setImage(Context context, CircleImageView view) {
        int iconRandom = (int)(Math.random()*icons.length);
        int colorRandom = (int)(Math.random()*colors.length);
        ColorDrawable color = new ColorDrawable(ContextCompat.getColor(context, colors[colorRandom]));
        Drawable image = ContextCompat.getDrawable(context, icons[iconRandom]);

        LayerDrawable ld = new LayerDrawable(new Drawable[]{color, image});

        view.setImageDrawable(ld);
    }

    public static void setImageWithPredefinedNums(Context context, CircleImageView view, int col,
                                                  int icon) {
        ColorDrawable color = new ColorDrawable(ContextCompat.getColor(context, colors[col]));
        Drawable image = ContextCompat.getDrawable(context, icons[icon]);

        LayerDrawable ld = new LayerDrawable(new Drawable[]{color, image});

        view.setImageDrawable(ld);
    }
}
