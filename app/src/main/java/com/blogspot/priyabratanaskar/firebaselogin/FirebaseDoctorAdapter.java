package com.blogspot.priyabratanaskar.firebaselogin;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.chip.Chip;

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
    protected void onBindViewHolder(@NonNull final FirebaseDoctorViewHolder holder, final int position, @NonNull final Doctor model) {
        holder.bindTo(model);

        //Handles click on ListItem
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(holder.doctorExperienceText.getContext(), String.valueOf(position),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(),DoctorDetailActivity.class);
                intent.putExtra(Constants.doctorName,model.getDoctorName());
                intent.putExtra(Constants.doctorExperience,model.getDoctorExperience());
                intent.putExtra(Constants.doctorQualification,model.getDoctorQualification());
                intent.putExtra(Constants.imageResource,model.getImageResource());
                intent.putExtra(Constants.position, position);
                intent.putExtra(Constants.fees,model.getFees());
                intent.putExtra(Constants.about,model.getAbout());
                v.getContext().startActivity(intent);
            }
        });

        holder.itemView.findViewById(R.id.share_check_box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"Sharing",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, model.getDoctorName() + "\n" + model.getDoctorQualification()
                        + "\n" + model.getDoctorExperience()+ "\n" + model.getTag());

                view.getContext().startActivity(Intent.createChooser(intent,"Share Using"));
                CheckBox checkBox = holder.itemView.findViewById(R.id.share_check_box);
                checkBox.setChecked(false);
            }
        });
    }

    @NonNull
    @Override
    public FirebaseDoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list,parent,false);
        return new FirebaseDoctorAdapter.FirebaseDoctorViewHolder(view);
    }

    public class FirebaseDoctorViewHolder extends RecyclerView.ViewHolder{
        private TextView doctorNameText;
        private TextView doctorQualificationText;
        private TextView doctorExperienceText;
        private ImageView doctorImage;
        private Chip materialChip;
        public FirebaseDoctorViewHolder(@NonNull View itemView) {
            super(itemView);

            doctorImage = itemView.findViewById(R.id.doctor_image);
            doctorNameText = itemView.findViewById(R.id.doctor_name);
            doctorExperienceText = itemView.findViewById(R.id.doctor_experience);
            doctorQualificationText = itemView.findViewById(R.id.doctor_qualification);
            materialChip = itemView.findViewById(R.id.doctor_chip);
        }
        void bindTo(Doctor currentDoctor) {
            doctorNameText.setText(currentDoctor.getDoctorName());
            doctorExperienceText.setText(currentDoctor.getDoctorExperience() + " years Experience");
            doctorQualificationText.setText(currentDoctor.getDoctorQualification());
            materialChip.setText(currentDoctor.getTag());
            //Load doctor image
            //Loading image from Glide library.
            Glide.with(doctorImage.getContext()).load(currentDoctor.getImageResource()).centerCrop().into(doctorImage);
        }
    }
}
