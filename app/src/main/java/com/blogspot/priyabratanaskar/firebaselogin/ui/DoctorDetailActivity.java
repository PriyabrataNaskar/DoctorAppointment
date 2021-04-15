package com.blogspot.priyabratanaskar.firebaselogin.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.priyabratanaskar.firebaselogin.Constants;
import com.blogspot.priyabratanaskar.firebaselogin.R;
import com.blogspot.priyabratanaskar.firebaselogin.adapters.FireBaseDoctorDetailAdapter;
import com.blogspot.priyabratanaskar.firebaselogin.model.Doctor;
import com.blogspot.priyabratanaskar.firebaselogin.model.DoctorDetailModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorDetailActivity extends AppCompatActivity {
    ImageView doctorImage;
    ProgressBar progressBar;
    TextView doctorName;
    TextView specializationTextView;
    TextView doctorFees;
    TextView doctorAbout;

    MaterialButton button;
    FirebaseAuth mAuth;

    private RecyclerView mRecyclerView;
    //private ArrayList<Doctor> mDoctorData;
    //private DoctorAdapter mDoctorAdapter;
    private FireBaseDoctorDetailAdapter firebaseDoctorDetailAdapter;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        doctorImage = findViewById(R.id.doctor_image);
        progressBar = findViewById(R.id.doctor_image_progress);
        doctorName = findViewById(R.id.name);
        specializationTextView = findViewById(R.id.specialization);
        doctorFees =findViewById(R.id.fees);
        doctorAbout = findViewById(R.id.about);
        button = findViewById(R.id.button);

        Intent intent = getIntent();
        Doctor doctor = new Doctor();
        String imageRes = intent.getStringExtra(Constants.imageResource);
        String name = intent.getStringExtra(Constants.doctorName);
        String qualification = intent.getStringExtra(Constants.doctorQualification);
        String tag = intent.getStringExtra(Constants.tag);
        int position = intent.getIntExtra(Constants.position,0);
        String experience = intent.getStringExtra(Constants.doctorExperience);
        String fees = intent.getStringExtra(Constants.fees);
        String about = intent.getStringExtra(Constants.about);
        doctor.setDoctorExperience(experience);
        doctor.setDoctorName(name);
        doctor.setDoctorQualification(qualification);
        doctor.setImageResource(imageRes);
        doctor.setTag(tag);

        Glide.with(this).load(imageRes).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                Toast.makeText(doctorImage.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).centerCrop().into(doctorImage);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Offline Booking Success",Toast.LENGTH_SHORT).show();
            }
        });
        doctorName.setText(name);
        specializationTextView.setText(qualification + " " + experience + "+ years of experience");
        doctorFees.setText(fees);
        doctorAbout.setText(about);

        //Get Firebase Ref
        mAuth = FirebaseAuth.getInstance();
        try {
            databaseReference = FirebaseDatabase.getInstance().getReference("doctor_detail/"+ "doctor_id_" + position);
            //Toast.makeText(this,"Position" + position,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        //storageReference = FirebaseStorage.getInstance().getReference().child("DoctorImages/");
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //It's a class provided by FirebaseUI to make a query to fetch appropriate data
        try {
            FirebaseRecyclerOptions<DoctorDetailModel> options = new FirebaseRecyclerOptions
                    .Builder<DoctorDetailModel>().setQuery(databaseReference, DoctorDetailModel.class).build();


            firebaseDoctorDetailAdapter = new FireBaseDoctorDetailAdapter(options);
            mRecyclerView.setAdapter(firebaseDoctorDetailAdapter);

            //Toast.makeText(this,options.getSnapshots().get(0).getDay_name(),Toast.LENGTH_SHORT).show();

            //mDoctorData = new ArrayList<>();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if (mAuth.getCurrentUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            firebaseDoctorDetailAdapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseDoctorDetailAdapter.stopListening();
    }

}