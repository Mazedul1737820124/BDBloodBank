package com.example.bdbloodbank;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;

public class SearchingList extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<CustomListView> list;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching_list);

        TextView dninf = findViewById(R.id.serTitle);
        SpannableString content = new SpannableString(dninf.getText().toString());
        content.setSpan(new UnderlineSpan(), 0, dninf.getText().toString().length(), 0);
        dninf.setText(content);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<CustomListView>();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        databaseReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot parent : dataSnapshot.getChildren()) {
                    CustomListView p = parent.getValue(CustomListView.class);
                    list.add(p);
                }
                adapter = new MyAdapter(SearchingList.this, list);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void search() {

//        database = FirebaseDatabase.getInstance();
//        databaseReference = database.getReference();
//        databaseReference.addValueEventListener(new ValueEventListener(){
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
//                String name = null;
//                String address = null;
//                String blood = null;
//                String available = "Available";
//                String ready = "Ready to donate";
//                //listView = findViewById(R.id.donorListShow);
//                CustomListView customListView;
//
//                for (DataSnapshot parent : dataSnapshot.getChildren()) {
//                    for (DataSnapshot child : parent.getChildren()){
//                        if("Name".equals(child.getKey())){
//                            name = child.getValue().toString();
//                        }
//                        if("Address".equals(child.getKey())){
//                            address = child.getValue().toString();
//                        }
//                        if("BloodGroup".equals(child.getKey())){
//                            blood = child.getValue().toString();
//                        }
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
}
