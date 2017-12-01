package com.pamon.app.pamon.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.pamon.app.pamon.R;
import com.pamon.app.pamon.adapter.AdapterDialogList;
import com.pamon.app.pamon.model.ModelDialogList;

import java.util.List;

/**
 * Created by Sofiyanudin on 16/11/2017.
 */

public class DialogList {
    AdapterDialogList adapterDialogList;
    AlertDialog alertDialog;
    ListView listView;
    public DialogList(Context context, List<ModelDialogList> model){


        adapterDialogList = new AdapterDialogList(context, model);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_list, null);

        listView = (ListView) view.findViewById(R.id.dialogListView);

        listView.setAdapter(adapterDialogList);

        builder.setView(view);

        builder.setCancelable(true);
        alertDialog = builder.create();
        alertDialog.getWindow().setLayout(700,600);
        adapterDialogList.notifyDataSetChanged();
    }

    public void show(){
        alertDialog.show();
    }
    public void dismiss(){
        alertDialog.dismiss();
    }
    public void notifyDataSetChanged(){
        adapterDialogList.notifyDataSetChanged();
    }
    public void setOnItemClickListener(final OnItemClickListener onItemClickListener){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
