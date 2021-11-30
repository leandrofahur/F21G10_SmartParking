package com.example.smartparking.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.smartparking.util.ClientStatusImage;

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

        if (view == null) {
            ImageView statusViewItem = new ImageView (viewGroup.getContext());
            statusViewItem.setImageResource(statusList.get(i).getStatusDraw());
            view = statusViewItem;
        }

        return view;
    }
}
