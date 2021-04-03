package com.blogspot.priyabratanaskar.firebaselogin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FirebaseDoctorAdapter extends FirebaseRecyclerAdapter<Doctor,FirebaseDoctorAdapter.FirebaseDoctorViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FirebaseDoctorAdapter(@NonNull FirebaseRecyclerOptions<Doctor> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final FirebaseDoctorViewHolder holder, final int position, @NonNull Doctor model) {
        holder.bindTo(model);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.doctorExperienceText.getContext(), String.valueOf(position),Toast.LENGTH_LONG).show();
            }
        });
    }

    @NonNull
    @Override
    public FirebaseDoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list,parent,false);
        return new FirebaseDoctorAdapter.FirebaseDoctorViewHolder(view);
    }

    public class FirebaseDoctorViewHolder extends RecyclerView.ViewHolder {
        private TextView doctorNameText;
        private TextView doctorQualificationText;
        private TextView doctorExperienceText;
        private ImageView doctorImage;
        public FirebaseDoctorViewHolder(@NonNull View itemView) {
            super(itemView);

            doctorImage = itemView.findViewById(R.id.doctor_image);
            doctorNameText = itemView.findViewById(R.id.doctor_name);
            doctorExperienceText = itemView.findViewById(R.id.doctor_experience);
            doctorQualificationText = itemView.findViewById(R.id.doctor_qualification);
        }
        void bindTo(Doctor currentDoctor) {
            doctorNameText.setText(currentDoctor.getDoctorName());
            doctorExperienceText.setText(currentDoctor.getDoctorExperience());
            doctorQualificationText.setText(currentDoctor.getDoctorQualification());
            //Load doctor image
            //Loading image from Glide library.
            Glide.with(doctorImage.getContext()).load(currentDoctor.getImageResource()).into(doctorImage);
        }
    }
}
