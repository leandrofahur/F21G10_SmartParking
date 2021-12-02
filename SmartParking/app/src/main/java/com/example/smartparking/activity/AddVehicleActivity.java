package com.example.smartparking.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartparking.R;
import com.example.smartparking.model.User;
import com.example.smartparking.model.UserViewModel;
import com.example.smartparking.model.Vehicle;
import com.example.smartparking.model.VehicleViewModel;
import com.example.smartparking.repository.UserRepository;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddVehicleActivity extends AppCompatActivity {

    private EditText editTextLicensePlate;
    private EditText editTextMakeAndModel;
    private EditText editTextModel;
    private EditText editTextColor;

    private Button addVehicleSubmitBtn;
    private Button backBtn;

    private String email;

    // DB:
    VehicleViewModel vehicleViewModel;
    UserViewModel userViewModel;
    List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        //Toast.makeText(this,email, Toast.LENGTH_SHORT).show();

        userViewModel = new ViewModelProvider.AndroidViewModelFactory(AddVehicleActivity.this.getApplication()).create(UserViewModel.class);
        vehicleViewModel = new ViewModelProvider.AndroidViewModelFactory(AddVehicleActivity.this.getApplication()).create(VehicleViewModel.class);

        email = getIntent().getExtras().getString("Email");

        userViewModel.getAllUsers().observe(this, users -> {
            for(User user: users) {
                userList.add(user);
            }
        });

        editTextLicensePlate = findViewById(R.id.editTextLicensePlate);
        editTextMakeAndModel = findViewById(R.id.editTextMakeAndModel);
        editTextModel = findViewById(R.id.editTextModel);
        editTextColor = findViewById(R.id.editTextColor);

        addVehicleSubmitBtn = findViewById(R.id.addVehicleSubmitBtn);
        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(view -> {
            Intent profileIntent = new Intent(
                    AddVehicleActivity.this, ProfileActivity.class);
            profileIntent.putExtra("Email",email);
            startActivity(profileIntent);
            finish();
        });

        addVehicleSubmitBtn.setOnClickListener(view -> {
            if(editTextLicensePlate.getText().toString().isEmpty()) {
                Toast.makeText(this, "License Plate cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                for (int i = 0; i < userList.size(); i++) {
                    if(userList.get(i).getEmail().equals(email)) {
                        VehicleViewModel.insertVehicle(new Vehicle(editTextLicensePlate.getText().toString(), editTextMakeAndModel.getText().toString() ,editTextModel.getText().toString(), editTextColor.getText().toString(), userList.get(i).getUserId()));
                        Intent profileIntent = new Intent(
                                AddVehicleActivity.this, ProfileActivity.class);
                        profileIntent.putExtra("Email",email);
                        startActivity(profileIntent);
                        finish();
                        break;
                    } else{
                        //Toast.makeText(this, "Problem with user from db", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}