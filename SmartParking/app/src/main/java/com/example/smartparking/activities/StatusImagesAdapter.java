package com.example.smartparking.activities;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class StatusImagesAdapter extends BaseAdapter {
    List<ClientStatusImage> statusList;

    public StatusImagesAdapter(List<ClientStatusImage> statusList) { this.statusList = statusList; }

    @Override
    public int getCount() {
        return statusList.size();
    }

    @Override
    public Object getItem(int i) {
        return statusList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return statusList.get(i).getStatusId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //needs to be implemented

        return view;
    }
}
