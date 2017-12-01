package com.pamon.app.pamon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.pamon.app.pamon.R;
import com.pamon.app.pamon.model.ModelDialogList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sofiyanudin on 16/11/2017.
 */

public class AdapterDialogList extends BaseAdapter {
    private List<ModelDialogList> model = new ArrayList<ModelDialogList>();
    LayoutInflater layoutInflater;

    public AdapterDialogList(Context context, List<ModelDialogList> model){
        this.model = model;
        this.layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int position) {
        return model.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new ViewHolder();

            convertView = layoutInflater.inflate(R.layout.dialog_list_card,parent,false);

            viewHolder.name = (TextView)convertView.findViewById(R.id.dialogListName);



            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(model.get(position).getName());



        return convertView;
    }

    public static class ViewHolder{
        TextView name;
    }


}
