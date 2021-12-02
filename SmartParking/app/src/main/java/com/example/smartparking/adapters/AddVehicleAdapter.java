package com.example.smartparking.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartparking.R;
import com.example.smartparking.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class AddVehicleAdapter extends BaseAdapter {
    List<Vehicle> listVehicle = new ArrayList<>();

    public AddVehicleAdapter(List<Vehicle> listVehicle) {
        this.listVehicle = listVehicle;
    }

    @Override
    public int getCount() {
        return listVehicle.size();
    }

    @Override
    public Object getItem(int position) {
        return listVehicle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_addvehicle, parent, false);
        }

        TextView textViewLicensePlate = convertView.findViewById(R.id.textViewLicensePlate);
        TextView textViewModel = convertView.findViewById(R.id.textViewModel);
        TextView textViewMake = convertView.findViewById(R.id.textViewMake);
        TextView textViewColour = convertView.findViewById(R.id.textViewColour);

        textViewLicensePlate.setText(listVehicle.get(position).getLicensePlate());
        textViewLicensePlate.setTextColor(Color.BLACK);

        textViewModel.setText(listVehicle.get(position).getModel());
        textViewModel.setTextColor(Color.BLACK);

        textViewMake.setText(listVehicle.get(position).getMake());
        textViewMake.setTextColor(Color.BLACK);

        textViewColour.setText(listVehicle.get(position).getColor());
        textViewColour.setTextColor(Color.BLACK);

        return convertView;
    }
}
