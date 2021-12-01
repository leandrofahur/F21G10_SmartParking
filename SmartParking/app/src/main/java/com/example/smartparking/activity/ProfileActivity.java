package com.example.smartparking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartparking.R;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    private TextView txtViewUserName;
    private Button addVehicleBtn;
    private Button addSubscriptionBtn;
    private Button cancelSubscriptionBtn;
    private Button viewInvoiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtViewUserName = findViewById(R.id.txtViewUserName);
        txtViewUserName.setText(getIntent().getExtras().getString("Email"));

        //addVehicleBtn = findViewById(R.id.addVehicleBtn);
        //addVehicleBtn.setOnClickListener(view->{
        //    setContentView(R.layout.layout_addvehicle);
        //});

        //addSubscriptionBtn = findViewById(R.id.addSubscriptionBtn);
        //cancelSubscriptionBtn = findViewById(R.id.cancelSubscriptionBtn);
        //viewInvoiceBtn = findViewById(R.id.viewInvoiceBtn);
    }
}