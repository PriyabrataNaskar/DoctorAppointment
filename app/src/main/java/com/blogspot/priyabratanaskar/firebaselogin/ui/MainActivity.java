package com.blogspot.priyabratanaskar.firebaselogin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.priyabratanaskar.firebaselogin.R;
import com.blogspot.priyabratanaskar.firebaselogin.adapters.FirebaseDoctorAdapter;
import com.blogspot.priyabratanaskar.firebaselogin.model.Doctor;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    private RecyclerView mRecyclerView;
    //private ArrayList<Doctor> mDoctorData;
    //private DoctorAdapter mDoctorAdapter;
    private FirebaseDoctorAdapter firebaseDoctorAdapter;
    private DatabaseReference databaseReference;

    //private StorageReference storageReference;
    // Folder path for Firebase Storage.
    //String Storage_Path = "DoctorImages/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        try {
            databaseReference = FirebaseDatabase.getInstance().getReference("doctor");
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        //storageReference = FirebaseStorage.getInstance().getReference().child("DoctorImages/");
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //It's a class provided by FirebaseUI to make a query to fetch appropriate data
        try {
            FirebaseRecyclerOptions<Doctor> options = new FirebaseRecyclerOptions
                    .Builder<Doctor>().setQuery(databaseReference, Doctor.class).build();


            firebaseDoctorAdapter = new FirebaseDoctorAdapter(options);
            mRecyclerView.setAdapter(firebaseDoctorAdapter);
            //mDoctorData = new ArrayList<>();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        //mDoctorAdapter = new DoctorAdapter(mDoctorData,this);
        //mRecyclerView.setAdapter(mDoctorAdapter);

        //initializeData();
    }

//    private void initializeData() {
//        mDoctorData.clear();
//        for(int i = 0; i<12;i++) {
//            Random random = new Random();
//            int years = random.nextInt(5);
//            mDoctorData.add(new Doctor(0, "Dr. Name " + (i+1), String.valueOf(years) + "+" + "years", "MBBS"));
//        }
//        mDoctorAdapter.notifyDataSetChanged();
//    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if (mAuth.getCurrentUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            firebaseDoctorAdapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseDoctorAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menu_log_out:
                FirebaseAuth
                        .getInstance().signOut();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.menu_my_profile:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.menu_news:
                startActivity(new Intent(this, NewsActivity.class));
                break;
        }
        return true;
    }
}
