package com.example.bdbloodbank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<CustomListView>profile;

    public  MyAdapter(Context c , ArrayList<CustomListView> p){
        context = c;
        profile = p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.dName.setText(profile.get(position).getName());
        holder.dReady.setText(profile.get(position).getLastDonationDate());
        holder.dAvailable.setText(profile.get(position).getEnableSer());
        holder.dBlood.setText(profile.get(position).getBloodGroup());
        holder.dAddress.setText(profile.get(position).getDistrict());

    }

    @Override
    public int getItemCount() {
        return profile.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView dName,dAddress,dBlood,dReady,dAvailable;
        public MyViewHolder(View itemView){
            super(itemView);
            dName = itemView.findViewById(R.id.serName);
            dAddress = itemView.findViewById(R.id.serAddress);
            dBlood = itemView.findViewById(R.id.serBlood);
            dAvailable = itemView.findViewById(R.id.serAvailable);
            dReady = itemView.findViewById(R.id.serReadyToDonate);
        }

    }

}
