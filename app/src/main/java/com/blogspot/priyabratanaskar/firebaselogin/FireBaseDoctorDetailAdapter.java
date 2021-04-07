package com.blogspot.priyabratanaskar.firebaselogin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.chip.Chip;

/**
 * Created by Priyabrata Naskar on 07-04-2021.
 */
public class FireBaseDoctorDetailAdapter extends FirebaseRecyclerAdapter<DoctorDetailModel,FireBaseDoctorDetailAdapter.FirebaseDoctorDetailViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FireBaseDoctorDetailAdapter(@NonNull FirebaseRecyclerOptions<DoctorDetailModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FirebaseDoctorDetailViewHolder holder, int position, @NonNull DoctorDetailModel model) {
        holder.bindTo(model);
        //Toast.makeText(holder.itemView.getContext(),model.getDayName(),Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    public FirebaseDoctorDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item,parent,false);
        //Toast.makeText(parent.getContext(),"Test",Toast.LENGTH_SHORT).show();
        return new FireBaseDoctorDetailAdapter.FirebaseDoctorDetailViewHolder(view);
    }

    public class FirebaseDoctorDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView dayName;
        private Chip timeTextView;
        //private TextView endTimeTextView;
        private TextView locationTextView;
        public FirebaseDoctorDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            dayName = itemView.findViewById(R.id.day_name_text);
            timeTextView = itemView.findViewById(R.id.timing_chip);
            locationTextView = itemView.findViewById(R.id.location);
            dayName = itemView.findViewById(R.id.day_name_text);

        }

        void bindTo(DoctorDetailModel model) {
            Toast.makeText(dayName.getContext(),"Name: " + model.getDay_name(),Toast.LENGTH_SHORT).show();
            dayName.setText(model.getDay_name());
            timeTextView.setText(model.getStart_time() + " - "+ model.getEnd_time());
            locationTextView.setText(model.getLocation());
        }
    }
}
