package com.example.bdbloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Searching extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        selectDistrictName();
        selectBoodGroup();

        Button ser = findViewById(R.id.sechigButton);
        ser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Searching.this,SearchingList.class));
            }
        });
    }

    //select district name
    void selectDistrictName(){

        Spinner districtName = findViewById(R.id.districtSearch);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.district_names2, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtName.setAdapter(adapter);
        districtName.setOnItemSelectedListener(this);
    }

    //select blood group
    void selectBoodGroup(){
        Spinner blood = findViewById(R.id.bloodSearch);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.bloodGroup, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blood.setAdapter(adapter);
        blood.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spin = (Spinner)parent;

        if(spin.getId() == R.id.districtSearch) {
            //select district name
            String disctrict = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), disctrict, Toast.LENGTH_SHORT).show();

        }

        else if(spin.getId() == R.id.bloodSearch) {
            //select user blood group
            String bloodG = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), bloodG, Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
