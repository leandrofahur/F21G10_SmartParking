package com.example.smartparking.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.smartparking.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddVehicleActivity extends AppCompatActivity {

    private EditText editTextLicensePlate;
    private EditText editTextMakeAndModel;
    private EditText editTextModel;
    private EditText editTextColor;

    private Button addVehicleSubmitBtn;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        editTextLicensePlate = findViewById(R.id.editTextLicensePlate);
        editTextMakeAndModel = findViewById(R.id.editTextMakeAndModel);
        editTextModel = findViewById(R.id.editTextModel);
        editTextColor = findViewById(R.id.editTextColor);

        addVehicleSubmitBtn = findViewById(R.id.addVehicleSubmitBtn);
        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(view -> {
            Intent profileIntent = new Intent(
                    AddVehicleActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
            finish();
        });

        addVehicleSubmitBtn.setOnClickListener(view -> {
            if(editTextLicensePlate.getText().toString().isEmpty()) {
                Toast.makeText(this, "License Plate cannot be empty", Toast.LENGTH_SHORT).show();
            } else {

            }
        });

    }
}