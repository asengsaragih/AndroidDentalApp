package com.mobile.dental.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mobile.dental.R;
import com.mobile.dental.model.Doctor;

import java.util.List;

public class SpinnerDoctorAdapter extends ArrayAdapter<Doctor> {

    public SpinnerDoctorAdapter(@NonNull Context context, List<Doctor> doctors) {
        super(context, 0, doctors);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return doctorFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView tvName = convertView.findViewById(android.R.id.text1);

        Doctor doctor = getItem(position);

        if (doctor != null) {
            if (doctor.getStatus().equals("Unavailable")){
                tvName.setText(doctor.getName());
            }
        }

        return convertView;
    }

    private final Filter doctorFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            return null;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Doctor) resultValue).getName();
        }
    };
}
