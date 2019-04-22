package com.example.hairstyle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private List<ListItemComments> listItemComments;
    private Context context;


    public CommentsAdapter(List<ListItemComments> listItemComments, Context context) {
        this.listItemComments = listItemComments;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_comments,parent,false);

        return new ViewHolder(v);
    }




    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i) {
        ListItemComments listComment =  listItemComments.get(i);
        viewHolder.textViewNameTitular.setText(listComment.getName_comments());
        viewHolder.textViewComments.setText(listComment.getDesc_comments());
        viewHolder.ratingBarComments.setNumStars(listComment.getRating_star());


    }

    @Override
    public int getItemCount() {
        return listItemComments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewNameTitular;
        public TextView textViewComments;
        public RatingBar ratingBarComments;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNameTitular = (TextView) itemView.findViewById(R.id.name_titular_comments);
            textViewComments = (TextView) itemView.findViewById(R.id.id_text_desc_comments);
            ratingBarComments = (RatingBar) itemView.findViewById(R.id.id_rating_start_comments);

        }
    }


}

