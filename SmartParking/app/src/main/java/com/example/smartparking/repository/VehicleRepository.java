package com.example.smartparking.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.smartparking.dao.VehicleDAO;
import com.example.smartparking.database.SmartParkingRoomDB;
import com.example.smartparking.model.Vehicle;

import java.util.List;

public class VehicleRepository {
    private VehicleDAO vehicleDAO;
    private LiveData<List<Vehicle>> listLiveData;
    private Vehicle vehicle;
    private List<Vehicle> vehicleList;

    public VehicleRepository(Application application) {
        SmartParkingRoomDB db = SmartParkingRoomDB.getDatabase(application);
        vehicleDAO = db.vehicleDAO();
        listLiveData = vehicleDAO.getAllVehicles();
    }

    public LiveData<List<Vehicle>> getAllData() {
        return listLiveData;
    }

    public void insert(Vehicle vehicle) {
        SmartParkingRoomDB.databaseWriteExecutor.execute(() -> vehicleDAO.insertVehicle(vehicle));
    }


    public Vehicle getVehicle (Integer vehicleId) {
        SmartParkingRoomDB.databaseWriteExecutor.execute(() -> vehicle = vehicleDAO.getVehicle(vehicleId));
        return vehicle;
    }

    public List<Vehicle> getVehicleByUserId (Integer userId) {
        SmartParkingRoomDB.databaseWriteExecutor.execute(() -> vehicleList = vehicleDAO.getVehicleByUserId(userId));
        return vehicleList;
    }
}
