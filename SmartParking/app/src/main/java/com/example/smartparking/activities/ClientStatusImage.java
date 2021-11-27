package com.example.smartparking.activities;

public class ClientStatusImage {
    int statusId;
    String statusName;
    int statusDraw;

    public ClientStatusImage(int statusId, String statusName, int statusDraw) {
        this.statusId = statusId;
        this.statusName = statusName;
        this.statusDraw = statusDraw;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getStatusDraw() {
        return statusDraw;
    }

    public void setStatusDraw(int statusDraw) {
        this.statusDraw = statusDraw;
    }
}
