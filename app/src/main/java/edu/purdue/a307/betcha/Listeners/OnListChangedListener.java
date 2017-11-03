package edu.purdue.a307.betcha.Listeners;

import android.app.Activity;
import android.content.Context;

/**
 * Created by kyleohanian on 11/3/17.
 */

public interface OnListChangedListener {
    Activity getActivity();
    Context getContext();
    void onListChanged();
}
