package com.example.smartparking.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "vehicles", foreignKeys = {@ForeignKey(entity = User.class,
        parentColumns = "userid",
        childColumns = "userid",
        onDelete = ForeignKey.CASCADE)})
public class Vehicle {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="vehicleid")
    private int vehicleId;

    @ColumnInfo(name="licenseplate")
    private String licensePlate;

    @ColumnInfo(name="make")
    private String make;

    @ColumnInfo(name="model")
    private String model;

    @ColumnInfo(name="color")
    private String color;

    @NonNull
    @ColumnInfo(name="userid")
    private Integer userId;

    public Vehicle() {
        // default constructor...
    }

    public Vehicle(@NonNull String licensePlate, String make, String model, String color, @NonNull Integer userId) {
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.color = color;
        this.userId = userId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    @NonNull
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(@NonNull String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @NonNull
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(@NonNull Integer userId) {
        this.userId = userId;
    }
}
