package com.blogspot.priyabratanaskar.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class DoctorDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        Intent intent = getIntent();
        Doctor doctor = new Doctor();
        String imageRes = intent.getStringExtra(Constants.imageResource);
        String name = intent.getStringExtra(Constants.doctorName);
        String qualification = intent.getStringExtra(Constants.doctorQualification);
        String tag = intent.getStringExtra(Constants.tag);
        String experience = intent.getStringExtra(Constants.doctorExperience);
        doctor.setDoctorExperience(experience);
        doctor.setDoctorName(name);
        doctor.setDoctorQualification(qualification);
        doctor.setImageResource(imageRes);
        doctor.setTag(tag);
    }
}