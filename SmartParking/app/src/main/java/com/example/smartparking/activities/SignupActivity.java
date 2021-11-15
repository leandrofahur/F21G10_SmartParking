package com.example.smartparking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smartparking.R;

public class SignupActivity extends AppCompatActivity {

    private Button buttonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        buttonSignup = findViewById(R.id.buttonSignup);

        // check database and route the user, if valid, to the user activity:
        buttonSignup.setOnClickListener((View view) -> {
            //String username = textViewLayoutUsername.getEditText().getText().toString();
            //String password = textViewLayoutPassword.getEditText().getText().toString();

            //if(username.isEmpty() || password.isEmpty()) {
            //    Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            //    return;
            //}

            Intent signupIntent = new Intent(SignupActivity.this, UserActivity.class);
            startActivity(signupIntent);
        });
    }
}