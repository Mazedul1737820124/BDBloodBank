package com.example.bdbloodbank;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchingList extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching_list);

        search();
        //underline
        TextView dninf = findViewById(R.id.serTitle);
        SpannableString content = new SpannableString(dninf.getText().toString());
        content.setSpan(new UnderlineSpan(), 0, dninf.getText().toString().length(), 0);
        dninf.setText(content);
    }

    public void search() {

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        databaseReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                String name = null;
                String address = null;
                String blood = null;
                String available = "Available";
                String ready = "Ready to donate";
                listView = findViewById(R.id.donorListShow);
                CustomListView customListView;

                for (DataSnapshot parent : dataSnapshot.getChildren()) {
                    for (DataSnapshot child : parent.getChildren()){
                        if("Name".equals(child.getKey())){
                            name = child.getValue().toString();
                        }
                        if("Address".equals(child.getKey())){
                            address = child.getValue().toString();
                        }
                        if("BloodGroup".equals(child.getKey())){
                            blood = child.getValue().toString();
                        }
                    }

                    customListView = new CustomListView(SearchingList.this,name,address,
                            ready, available,blood);
                    listView.setAdapter(customListView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
