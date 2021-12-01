package com.example.smartparking.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.smartparking.repository.InvoiceRepository;
import com.example.smartparking.repository.VehicleRepository;

import java.util.List;

public class vehicleViewModel extends AndroidViewModel {
    public static VehicleRepository vehicleRepository;
    public final LiveData<List<Vehicle>> getAllVehicles;
    public Vehicle getVehicle;

    public vehicleViewModel(@NonNull Application application) {
        super(application);
        vehicleRepository = new VehicleRepository(application);
        getAllVehicles = vehicleRepository.getAllData();
    }

    public LiveData<List<Vehicle>> getAllVehicle() {
        return getAllVehicles;
    }

    public static void insertVehicle(Vehicle vehicle) { vehicleRepository.insert(vehicle);}

    public static Vehicle getVehicle(int vehicleId) { return vehicleRepository.getVehicle(vehicleId);}
}
