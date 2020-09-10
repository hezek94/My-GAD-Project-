package com.s.mygadproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.s.mygadproject.save.LeadersItemPojo;

import java.util.ArrayList;

public class LearnersAdapter extends RecyclerView.Adapter<LearnersAdapter.viewHolder> {
    private ArrayList<LeadersItemPojo> itemPojos;
    private Context context;
    public LearnersAdapter(Context context, ArrayList<LeadersItemPojo> itemPojos){
        this.context= context;
        this.itemPojos=itemPojos;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.leader_layout, parent,false);
        return new LearnersAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.name.setText(itemPojos.get(position).getName());
        holder.country.setText(itemPojos.get(position).getCountry());
        holder.hours.setText(String.valueOf(itemPojos.get(position).getHours()));


    }

    @Override
    public int getItemCount() {
        return itemPojos.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name,hours,country;
        ImageView badge;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_one);
            hours= (TextView) itemView.findViewById(R.id.score);
            country=(TextView)itemView.findViewById(R.id.country_one);
           // badge=(ImageView)itemView.findViewById(R.id.load_image);
        }
    }
}
