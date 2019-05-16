package com.example.bdbloodbank;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.v4.os.LocaleListCompat.create;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public static String contactToDonor = null;
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.dName.setText(profile.get(position).getName());
        holder.dReady.setText(profile.get(position).getLastDonationDate());
        holder.dAvailable.setText(profile.get(position).getEnableSer());
        holder.dBlood.setText(profile.get(position).getBloodGroup());
        holder.dAddress.setText(profile.get(position).getDistrict());

        if("Ready to donate".equals(profile.get(position).getLastDonationDate())){
            holder.dAvailable.setText("Available");
            holder.dAvailable.setTextColor(Color.rgb(14,211,14));
        }
        else {
            holder.dAvailable.setText("Hidden");
            holder.dAvailable.setTextColor(Color.BLACK);
        }
        holder.contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactToDonor = profile.get(position).getId();
                context.startActivity(new Intent(context,ContactToDonor.class));
            }
        });

    }
    @Override
    public int getItemCount() {
        return profile.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView dName,dAddress,dBlood,dReady,dAvailable;
        Button contact;
        TextView contactPhn;
        public MyViewHolder(View itemView){
            super(itemView);
            dName = itemView.findViewById(R.id.serName);
            dAddress = itemView.findViewById(R.id.serAddress);
            dBlood = itemView.findViewById(R.id.serBlood);
            dAvailable = itemView.findViewById(R.id.serAvailable);
            dReady = itemView.findViewById(R.id.serReadyToDonate);
            contact = itemView.findViewById(R.id.contactNow);
            contactPhn = itemView.findViewById(R.id.contactPhoneNo);
        }
    }
}
