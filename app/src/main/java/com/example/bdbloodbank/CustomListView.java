package com.example.bdbloodbank;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import static com.example.bdbloodbank.R.layout.donor_list_view;

public class CustomListView  extends ArrayAdapter<String>{
    private String name;
    private String address;
    private String readyToDonate;
    private String available;
    private String blood;
    private Activity context;


    public CustomListView(Activity context, String name, String addres,
                          String readyToDonate,
                          String available, String blood) {
        super(context,R.layout.donor_list_view);
        this.name = name;
        this.address = addres;
        this.readyToDonate = readyToDonate;
        this.available = available;
        this.context = context;
        this.blood = blood;
    }

    @NonNull
    @Override
    public View getView(int position, View converView , ViewGroup parent) {

        View r = converView;
        ViewHolder viewHolder = null;


        LayoutInflater layoutInflater = context.getLayoutInflater();
        r = layoutInflater.inflate(donor_list_view, null, true);

        viewHolder = new ViewHolder(r);
        r.setTag(viewHolder);


        viewHolder = (ViewHolder) r.getTag();

        viewHolder.dName.setText(name);
        viewHolder.dAvailable.setText(available);
        viewHolder.dBlood.setText(blood);
        viewHolder.dAddress.setText(address);
        viewHolder.dReady.setText(readyToDonate);

        return r;
    }

    class ViewHolder{

        TextView dName;
        TextView dAddress;
        TextView dReady;
        TextView dBlood;
        TextView dAvailable;

        ViewHolder(View v) {

            dName = v.findViewById(R.id.serName);
            dAddress = v.findViewById(R.id.serAddress);
            dReady = v.findViewById(R.id.serReadyToDonate);
            dBlood = v.findViewById(R.id.serBlood);
            dAvailable = v.findViewById(R.id.serAvailable);

        }
    }
}
