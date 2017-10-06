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
                R.drawable.icon_build_24dp, R.drawable.icon_cake_24dp};
    private static int[] colors = {R.color.iconColor1, R.color.iconColor2, R.color.iconColor3};

    public static void setImage(Context context, CircleImageView view) {
        int iconRandom = (int)(Math.random()*3);
        int colorRandom = (int)(Math.random()*3);
        ColorDrawable color = new ColorDrawable(ContextCompat.getColor(context, colors[colorRandom]));
        Drawable image = ContextCompat.getDrawable(context, icons[iconRandom]);

        LayerDrawable ld = new LayerDrawable(new Drawable[]{color, image});

        view.setImageDrawable(ld);
    }
}
