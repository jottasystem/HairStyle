package com.example.hairstyle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {

    Context myContenxt;
    List<cardItem> mData;


    public Adapter(Context myContenxt, List<cardItem> mData) {
        this.myContenxt = myContenxt;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater =  LayoutInflater.from(myContenxt);

        View v = inflater.inflate(R.layout.service_card_item,viewGroup,false);

        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int position) {



        myViewHolder.imgCard.setImageResource(mData.get(position).getImgCard());

        myViewHolder.title.setText(mData.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {


        ImageView imgCard ;

        TextView title;




        public myViewHolder(@NonNull View itemView) {

            super(itemView);

            imgCard =  itemView.findViewById(R.id.card_background_image);
            title = itemView.findViewById(R.id.text_service);


        }
    }
}
