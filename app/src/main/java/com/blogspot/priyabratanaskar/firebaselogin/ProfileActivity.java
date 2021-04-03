package com.blogspot.priyabratanaskar.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Spinner genderSpinner;
    private static final String[] spinnerResourceString = {"Male", "Female", "Other"};
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        genderSpinner = findViewById(R.id.gender);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProfileActivity.this,
                android.R.layout.simple_spinner_item,spinnerResourceString);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);
        genderSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        gender = spinnerResourceString[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
