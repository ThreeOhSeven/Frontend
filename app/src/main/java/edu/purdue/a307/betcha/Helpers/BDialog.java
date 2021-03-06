package edu.purdue.a307.betcha.Helpers;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import edu.purdue.a307.betcha.Listeners.AlertDialogListener;

/**
 * Created by kyleohanian on 10/21/17.
 */

public class BDialog {

    public static void showSignOut(Context context, final AlertDialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("Sign out");
        builder.setMessage("Are you sure you would like to sign out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onPositive();
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onNegative();
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


    public static void showDeleteAccount(Context context, final AlertDialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("Sign out");
        builder.setMessage("Are you sure you would like to delete your account? " +
                "All of your data will be deleted.");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onPositive();
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onNegative();
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


    public static void confirmBet(Context context, String side, final AlertDialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm Completion");
        builder.setMessage("Are you sure you would like to complete this bet? Please confirm that the " +
                "winning side is \"" + side + "\"");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onPositive();
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onNegative();
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public static void deleteBet(Context context, final AlertDialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm Deletion");
        builder.setMessage("Are you sure you would like to delete this bet? Please confirm deletion");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onPositive();
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onNegative();
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}
