package com.example.smartparking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartparking.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    private FloatingActionButton addVehicleBtn;
    private Button bookSpotBtn;
    private Button logOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        addVehicleBtn = findViewById(R.id.addVehicleBtn);

        addVehicleBtn.setOnClickListener(view -> {
            Intent AddVehicleIntent = new Intent(ProfileActivity.this, AddVehicleActivity.class);
            startActivity(AddVehicleIntent);
            finish();
        });


        //bookSpotBtn = findViewById(R.id.bookYourSpotBtn);
        //logOutBtn = findViewById(R.id.logOutBtn);
    }
}