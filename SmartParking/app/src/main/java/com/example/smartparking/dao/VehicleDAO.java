package com.example.smartparking.dao;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.smartparking.model.Vehicle;

import java.util.List;

@Dao
public interface VehicleDAO {
    @Query("SELECT * FROM vehicles")
    LiveData<List<Vehicle>> getAllVehicles();

    @Query("SELECT * FROM vehicles WHERE vehicleid=:vehicleid")
    Vehicle getInvoice(int vehicleid);

    @Query("DELETE FROM vehicles")
    void deleteAllVehicles();

    @Insert(onConflict = IGNORE)
    void insertVehicle(Vehicle vehicle);
}
