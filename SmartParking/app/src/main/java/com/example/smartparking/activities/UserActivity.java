package com.example.smartparking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.smartparking.R;

public class UserActivity extends AppCompatActivity {

    TextView textViewUserTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        textViewUserTitle = findViewById(R.id.textViewUserTitle);
    }
}