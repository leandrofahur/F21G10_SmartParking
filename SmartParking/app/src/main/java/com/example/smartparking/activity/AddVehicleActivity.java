package com.example.smartparking.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.smartparking.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(view -> {
            Intent profileIntent = new Intent(
                    AddVehicleActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
            finish();
        });
    }
}