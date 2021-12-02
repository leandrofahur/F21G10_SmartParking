package com.example.smartparking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartparking.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewEmail;
    private FloatingActionButton addVehicleBtn;
    private Button bookSpotBtn;
    private Button logOutBtn;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // get the email form the user:
        email = getIntent().getExtras().getString("Email");
        Toast.makeText(this,email, Toast.LENGTH_SHORT).show();

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