package com.example.bdbloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import database.InsertData;

import static android.widget.ArrayAdapter.*;
import static database.InsertData.primaryKey;

public class RegistrationForm extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    String bloodG,discrict;

    private String  Phone, Email,Name, Password,
            District,Address,BloodGroup,
            EnableSer,Log,LastDonationDate;
    EditText col1;
    EditText col2;
    EditText col3;
    EditText col4;
    EditText col5;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        selectDistrictName();
        selectBloodGroup();
        Button reg;
        reg = (Button) findViewById(R.id.userSignUp);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAccountRegistration();
            }
        });

    }


    //call spinner method and select user district name
    public void selectDistrictName() {

        Spinner districtName = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = createFromResource(this, R.array.district_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtName.setAdapter(adapter);
        districtName.setOnItemSelectedListener(this);
    }

    @Override
     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spin = (Spinner)parent;
        Spinner spin2 = (Spinner)parent;
        if(spin.getId() == R.id.spinner) {
            //select district name
            discrict = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), discrict, Toast.LENGTH_SHORT).show();

        }

        else if(spin2.getId() == R.id.bloodGroupList) {
            //select user blood group
            bloodG = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), bloodG, Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    //Select blood group of user
    public void  selectBloodGroup(){

        Spinner blood = findViewById(R.id.bloodGroupList);


        ArrayAdapter<CharSequence> adapter = createFromResource(this, R.array.bloodGroup, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blood.setAdapter(adapter);
        blood.setOnItemSelectedListener(this);

    }

    //User account registration
    protected void userAccountRegistration(){

        col1 = (EditText)findViewById(R.id.userRegPhoneNumber);
        Phone = col1.getText().toString();

        col2  = (EditText)findViewById(R.id.userRegEmail);
        Email = col2.getText().toString();

        col3 = (EditText)findViewById(R.id.userRegName);
        Name = col3.getText().toString();

        col4  = (EditText)findViewById(R.id.userRegPassword);
        Password = col4.getText().toString();

        District  = discrict;

        col5 = (EditText)findViewById(R.id.userAddress);
        Address = col5.getText().toString();

        BloodGroup = bloodG;

        EnableSer = "true";
        Log = "true";
        LastDonationDate = "90";

        primaryKey = Phone;
        //push all data in database
        new InsertData( Phone, Email,Name, Password,
                District,Address,BloodGroup,
                EnableSer,Log,LastDonationDate);

        Toast.makeText(RegistrationForm.this, "Data inserted successfully", Toast.LENGTH_LONG).show();
        startActivity(new Intent(RegistrationForm.this, Verificaton.class));
        finish();

    }
}


