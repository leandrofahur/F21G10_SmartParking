package com.example.smartparking.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "invoices", foreignKeys = {@ForeignKey(entity = User.class,
        parentColumns = "userid",
        childColumns = "userid",
        onDelete = ForeignKey.CASCADE)})

public class Invoice {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="invoiceid")
    private Integer invoiceId;

    @NonNull
    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(@NonNull Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    @NonNull
    @ColumnInfo(name="startdate")
    private String startDate;

    @NonNull
    @ColumnInfo(name="enddate")
    private String endDate;

    @ColumnInfo(name="description")
    private String description;

    @NonNull
    @ColumnInfo(name="invoicecost")
    private Double totalCost;

    @NonNull
    @ColumnInfo(name="userid")
    private Integer userId;

    @Ignore
    private double rate;
    @Ignore
    private double duration;
    @Ignore
    private double VAN_RATE;
    @Ignore
    private double CAR_RATE;
    @Ignore
    private double BIKE_RATE;

    public Invoice() {
        //default
    }

    public Invoice(String description, int userId, @NonNull String startDate, @NonNull String endDate) {
        this.description = description;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = "Parking spot booked from " + startDate + " to " + endDate + " with a duration of "
                + duration + " hours and Total Cost of: " + String.format("%,.2f", totalCost);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @NonNull
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(@NonNull String startDate) {
        this.startDate = startDate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @NonNull
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(@NonNull String endDate) {
        this.endDate = endDate;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @NonNull
    public Double getTotalCost() {
        totalCost = 1.5 * duration;
        return totalCost;
    }

    public void setTotalCost(@NonNull Double totalCost) {
        this.totalCost = totalCost;
    }

    public double getVAN_RATE() {
        VAN_RATE = 2;
        return VAN_RATE;
    }

    public double getCAR_RATE() {
        CAR_RATE = 1.5;
        return CAR_RATE;
    }

    public double getBIKE_RATE() {
        BIKE_RATE = 1;
        return BIKE_RATE;
    }

    public void setVAN_RATE(double VAN_RATE) {
        this.VAN_RATE = VAN_RATE;
    }

    public void setCAR_RATE(double CAR_RATE) {
        this.CAR_RATE = CAR_RATE;
    }

    public void setBIKE_RATE(double BIKE_RATE) {
        this.BIKE_RATE = BIKE_RATE;
    }
}
