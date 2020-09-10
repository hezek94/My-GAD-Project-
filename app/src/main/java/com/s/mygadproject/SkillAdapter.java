package com.s.mygadproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.s.mygadproject.save.SkillItemPojo;

import java.util.ArrayList;


public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.viewholder> {
    private Context context;
    private ArrayList<SkillItemPojo> skillItemPojos;

    public SkillAdapter (Context context, ArrayList<SkillItemPojo> skillItemPojos){
        this.context= context;
        this.skillItemPojos=skillItemPojos;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent,false);
        return new SkillAdapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.name.setText(skillItemPojos.get(position).getName());
        holder.country.setText(skillItemPojos.get(position).getCountry());
        holder.score.setText(String.valueOf(skillItemPojos.get(position).getScore()));

    }

    @Override
    public int getItemCount() {
        return skillItemPojos.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView name,score,country;
        ImageView badge;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_one);
            score= (TextView) itemView.findViewById(R.id.score);
            country= (TextView) itemView.findViewById(R.id.country_one);
          //  badge = (ImageView) itemView.findViewById(R.id.load_image);
        }
    }
}
