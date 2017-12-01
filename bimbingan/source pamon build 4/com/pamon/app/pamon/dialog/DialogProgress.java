package com.pamon.app.pamon.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.pamon.app.pamon.R;


/**
 * Created by Sofiyanudin on 31/10/2017.
 */

public class DialogProgress {
    Context context;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    public DialogProgress(Context context){
        this.context = context;
        builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_progress, null);

        builder.setView(view);

        builder.setCancelable(false);

        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }
    public void show(){
        alertDialog.show();
    }
    public void dismiss(){
        alertDialog.dismiss();
    }
}
