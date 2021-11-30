package com.example.smartparking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.smartparking.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);

//        try {
//            Intent userIntent = new Intent(MainActivity.this, UserActivity.class);
//            startActivity(userIntent);
//        } catch(Exception ex) {
//            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
//        }

    }
}