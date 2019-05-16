package com.example.bdbloodbank;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import static database.InsertData.primaryKey;

public class MyAccount extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    TextView Name;
    TextView Blood;
    TextView District;
    TextView Address;
    TextView Phone;
    TextView dninf;

    Calendar c;
    DatePickerDialog datePickerDialog;
    Button getDate;
    int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        //underline
        dninf = findViewById(R.id.dninfo);
        SpannableString content = new SpannableString(dninf.getText().toString());
        content.setSpan(new UnderlineSpan(), 0, dninf.getText().toString().length(), 0);
        dninf.setText(content);
        userProfile();
        lastDonationDate();
    }

    public void userProfile() {
        Name = findViewById(R.id.donorName);
        Blood = findViewById(R.id.donorBloodGroup);
        District = findViewById(R.id.donorDistrictName);
        Address = findViewById(R.id.donorAddress);
        Phone = findViewById(R.id.donorPhoneNo);


        databaseReference.child(primaryKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    if("Name".equals(child.getKey())){
                        Name.setText(child.getValue().toString());
                    }
                    else if("BloodGroup".equals(child.getKey())){
                        Blood.setText(child.getValue().toString());
                    }
                    else if("District".equals(child.getKey())){
                        District.setText(child.getValue().toString());
                    }

                    else if("Address".equals(child.getKey())){
                        Address.setText(child.getValue().toString());
                    }

                    else if("Id".equals(child.getKey())){
                        Phone.setText(child.getValue().toString());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void lastDonationDate(){


        getDate = findViewById(R.id.setLastDonation);
        getDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(MyAccount.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myear, int mmonth, int mdayOfMonth) {
                        String dayDi = ""+mdayOfMonth;
                        String monthDi = ""+(mmonth+1);
                        if(dayDi.length() == 1){
                            dayDi = "0"+dayDi;
                        }
                        if(monthDi.length() == 1){
                            monthDi = "0"+monthDi;
                        }
                        databaseReference.child(primaryKey).child("LastDonationDate").setValue(myear+""+monthDi+dayDi);
                    }
                },day,month,year);
                datePickerDialog.show();
            }
        });
    }
}
