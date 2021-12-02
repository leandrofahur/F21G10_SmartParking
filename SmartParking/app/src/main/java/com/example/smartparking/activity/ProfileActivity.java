package com.example.smartparking.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartparking.R;
import com.example.smartparking.adapters.AddVehicleAdapter;
import com.example.smartparking.model.User;
import com.example.smartparking.model.UserViewModel;
import com.example.smartparking.model.Vehicle;
import com.example.smartparking.model.VehicleViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewEmail;
    private FloatingActionButton addVehicleBtn;
    private Button bookSpotBtn;
    private Button logOutBtn;
    private String email;
    private ListView listViewMyVehicles;

    // DB:
    VehicleViewModel vehicleViewModel;
    UserViewModel userViewModel;
    List<User> userList = new ArrayList<>();
    List<Vehicle> vehicleList = new ArrayList<>();
    List<Vehicle> vehicleList2 = new ArrayList<>();

    Integer usrId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // get the email form the user:
        email = getIntent().getExtras().getString("Email");
        Toast.makeText(this,email, Toast.LENGTH_SHORT).show();

        listViewMyVehicles = findViewById(R.id.listViewMyVehicles);


        userViewModel = new ViewModelProvider.AndroidViewModelFactory(ProfileActivity.this.getApplication()).create(UserViewModel.class);
        vehicleViewModel = new ViewModelProvider.AndroidViewModelFactory(ProfileActivity.this.getApplication()).create(VehicleViewModel.class);

        userViewModel.getAllUsers().observe(this, users -> {
            for(User user: users) {
                userList.add(user);
                if(user.getEmail().equals(email)) {
                    usrId = user.getUserId();
                }
            }
        });

        vehicleViewModel.getAllVehicles().observe(this, vehicles -> {
            for(Vehicle vehicle: vehicles) {
                if(usrId == vehicle.getUserId()) {
                    vehicleList.add(vehicle);
                }
            }
        });

        AddVehicleAdapter vehicleAdapter = new AddVehicleAdapter(vehicleList);
        listViewMyVehicles.setAdapter(vehicleAdapter);

        addVehicleBtn = findViewById(R.id.addVehicleBtn);

        addVehicleBtn.setOnClickListener(view -> {
            Intent addVehicleIntent = new Intent(ProfileActivity.this, AddVehicleActivity.class);
            addVehicleIntent.putExtra("Email",email);
            startActivity(addVehicleIntent);
            finish();
        });

        bookSpotBtn = findViewById(R.id.bookYourSpotBtn);
        bookSpotBtn.setOnClickListener(view ->{
            Intent mainIntent = new Intent(ProfileActivity.this, UserActivity.class);
            mainIntent.putExtra("Email",email);
            startActivity(mainIntent);
            finish();
        });

        logOutBtn = findViewById(R.id.logOutBtn);

        logOutBtn.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent mainIntent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        });
    }
}