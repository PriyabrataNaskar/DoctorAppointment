package com.blogspot.priyabratanaskar.firebaselogin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.priyabratanaskar.firebaselogin.model.Doctor;
import com.blogspot.priyabratanaskar.firebaselogin.R;

import java.util.ArrayList;

class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    private ArrayList<Doctor> mDoctorData;
    private Context mContext;

    /**
     * Constructor that passes doctor data to the context
     * @param mDoctorData
     * @param mContext
     */
    public DoctorAdapter(ArrayList<Doctor> mDoctorData, Context mContext) {
        this.mDoctorData = mDoctorData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.doctor_list,parent,false));
    }

    /**
     * Bind data to the view holder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.ViewHolder holder, int position) {
        Doctor currentDoctor = mDoctorData.get(position);
        holder.bindTo(currentDoctor);
    }

    @Override
    public int getItemCount() {
        return mDoctorData.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView doctorNameText;
        private TextView doctorQualificationText;
        private TextView doctorExperienceText;
        private ImageView doctorImage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorImage = itemView.findViewById(R.id.doctor_image);
            doctorNameText = itemView.findViewById(R.id.doctor_name);
            doctorQualificationText = itemView.findViewById(R.id.doctor_qualification);
            doctorExperienceText = itemView.findViewById(R.id.doctor_experience);

            //set onClickListener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Doctor currentDoctor = mDoctorData.get(getAdapterPosition());

            //opening doctor detail activity
        }

        void bindTo(Doctor currentDoctor) {
            doctorNameText.setText(currentDoctor.getDoctorName());
            doctorQualificationText.setText(currentDoctor.getDoctorQualification());
            doctorExperienceText.setText(currentDoctor.getDoctorExperience());

            //Load doctor image
        }
    }
}
