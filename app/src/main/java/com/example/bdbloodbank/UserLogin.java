package com.example.bdbloodbank;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import database.InsertData;

import static database.InsertData.primaryKey;


public class UserLogin extends AppCompatActivity {

    private  boolean showP = true;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String logPhone ;
    String logPassword;

    static int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        Button login;
        login = (Button) findViewById(R.id.userLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               checkValidUser();

            }
        });

        Button createAccount;
        createAccount = (Button) findViewById(R.id.createAnAccount);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLogin.this,RegistrationForm.class));
            }
        });


        final Button showPss;
        showPss = (Button) findViewById(R.id.showPassword);
        showPss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText getPassword = (EditText) findViewById(R.id.userPassword);

                if(showP){
                    showPss.setText("Hide");
                    getPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showP = false;
                }
                else {
                    showPss.setText("Show");
                    getPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showP = true;
                }
            }
        });
    }

    public void checkValidUser(){

        //check valid email or password
        EditText getEmail = (EditText) findViewById(R.id.userEmail);
        String phone = getEmail.getText().toString();
        InsertData.primaryKey = phone;

        EditText getPassword = (EditText) findViewById(R.id.userPassword);
        String password = getPassword.getText().toString();

        TextView message = (TextView)findViewById(R.id.messageField);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        databaseReference.child(phone).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    if("Id".equals(child.getKey())){
                        logPhone = child.getValue().toString();
                    }
                    else if("Password".equals(child.getKey())){
                        logPassword = child.getValue().toString();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        if(phone.equals(logPhone) && password.equals(logPassword)) {
            startActivity(new Intent(UserLogin.this, Controler.class));
            finish();
        }
        else{

            counter = counter + 1;
            if(counter == 2) {
                message.setText("The email or password you entered is incorrect. Please try again");
                message.setTextColor(Color.RED);
            }

        }

    }

}
