package com.example.bdbloodbank;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.bdbloodbank.MyAdapter.contactToDonor;
import static database.InsertData.primaryKey;

public class ContactToDonor extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    TextView name,blood,district,address,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_to_donor);
        userProfile();

        final Button phnCall = findViewById(R.id.phoneCall);
        phnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                phone = findViewById(R.id.contactPhoneNo);
                callIntent.setData(Uri.parse("tel:"+phone.getText().toString()));


                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(ContactToDonor.this,"Please grant permission",Toast.LENGTH_SHORT);
                    requestPermission();
                }else{
                    startActivity(callIntent);
                }

            }

        });
    }
    private void requestPermission(){
        ActivityCompat.requestPermissions(ContactToDonor.this,new String[] {Manifest.permission.CALL_PHONE},1);
    }


    public void userProfile() {
        name = findViewById(R.id.contactName);
        blood = findViewById(R.id.contactBloodGroup);
        district = findViewById(R.id.contactDistrictName);
        address = findViewById(R.id.contactAddress);
        phone = findViewById(R.id.contactPhoneNo);


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        databaseReference.child(contactToDonor).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    if("Name".equals(child.getKey())){
                        name.setText(child.getValue().toString());
                    }
                    else if("BloodGroup".equals(child.getKey())){
                        blood.setText(child.getValue().toString());
                    }
                    else if("District".equals(child.getKey())){
                        district.setText(child.getValue().toString());
                    }

                    else if("Address".equals(child.getKey())){
                        address.setText(child.getValue().toString());
                    }

                    else if("Id".equals(child.getKey())){
                        phone.setText(child.getValue().toString());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
