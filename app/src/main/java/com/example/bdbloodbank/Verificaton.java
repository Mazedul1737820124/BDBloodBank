package com.example.bdbloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;


public class Verificaton extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificaton);

       verifiedAccount();
    }

    //verified aacount
    void verifiedAccount(){

        Button verified = (Button) findViewById(R.id.verifyAccount);
        verified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Verificaton.this,Controler.class));
                finish();
            }
        });
    }

}
