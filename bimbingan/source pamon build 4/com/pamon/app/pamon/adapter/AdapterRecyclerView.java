package com.pamon.app.pamon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.pamon.app.pamon.R;
import com.pamon.app.pamon.model.CardRecyclerView;

import java.util.List;

/**
 * Created by Sofiyanudin on 05/11/2017.
 */

public class AdapterRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private OnItemClickListener onItemClickListener;
    List<CardRecyclerView> listCard;
    Context context;

    public AdapterRecyclerView(Context pcontext, List<CardRecyclerView> listCard){
        context = pcontext;
        this.listCard = listCard;
    }

    @Override
    public int getItemViewType(int position) {
        return listCard.get(position).getLayout();
    }

    class ViewHolderLayoutA extends RecyclerView.ViewHolder{
        TextView title, content;
        ImageView image;
        public ViewHolderLayoutA(View itemView) {
            super(itemView);
            title = ((TextView) itemView.findViewById(R.id.textTitle));
            content = ((TextView) itemView.findViewById(R.id.textContent));
            image = ((ImageView) itemView.findViewById(R.id.image));
        }
    }
    class ViewHolderLayoutB extends RecyclerView.ViewHolder{
        TextView title, content;
        ImageView image;
        public ViewHolderLayoutB(View itemView) {
            super(itemView);
            title = ((TextView) itemView.findViewById(R.id.textTitle));
            content = ((TextView) itemView.findViewById(R.id.textContent));
            image = ((ImageView) itemView.findViewById(R.id.image));
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 0 :
                View viewCardA = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_a,parent,false);
                return new ViewHolderLayoutA(viewCardA);
            case 1 :
                View viewCardB = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_b,parent,false);
                return new ViewHolderLayoutB(viewCardB);

            default:
                View viewCardAdef = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_a,parent,false);
                return new ViewHolderLayoutA(viewCardAdef);

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CardRecyclerView modelCard = listCard.get(position);
        switch (holder.getItemViewType()){
            case 0:
                ViewHolderLayoutA viewHolderA = (ViewHolderLayoutA) holder;
                viewHolderA.title.setText(modelCard.getTitle());
                viewHolderA.content.setText(modelCard.getContent());

                viewHolderA.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onItemClickListener != null){
                            onItemClickListener.onItemPosition(position);
                        }
                    }
                });
                break;
            case 1:
                ViewHolderLayoutB viewHolderB = (ViewHolderLayoutB) holder;
                viewHolderB.title.setText(modelCard.getTitle());
                viewHolderB.content.setText(modelCard.getContent());

                if(modelCard.getUrlImage() == null){
                    viewHolderB.image.setVisibility(View.GONE);
                }
                if(modelCard.getTitle() == null){
                    viewHolderB.title.setVisibility(View.GONE);
                }
                if(modelCard.getContent() == null){
                    viewHolderB.content.setVisibility(View.GONE);
                }
                viewHolderB.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onItemClickListener != null){
                            onItemClickListener.onItemPosition(position);
                        }
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        if(listCard !=null)
            return listCard.size();
        else
            return 0;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener{
        void onItemPosition(int position);
    }
}
