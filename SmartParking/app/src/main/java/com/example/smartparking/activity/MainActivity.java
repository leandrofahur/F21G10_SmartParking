package com.example.smartparking.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.smartparking.R;
import com.example.smartparking.model.User;
import com.example.smartparking.model.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userViewModel = new ViewModelProvider.AndroidViewModelFactory(
                MainActivity.this.getApplication()
        ).create(UserViewModel.class);

        userViewModel.getAllUsers().observe(this, users -> {
            Log.d("TAG", "onCreate() " + users.get(0).getEmail());
        });

        //Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        //startActivity(loginIntent);
    }
}