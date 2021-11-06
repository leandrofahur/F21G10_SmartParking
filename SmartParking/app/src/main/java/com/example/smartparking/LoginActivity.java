package com.example.smartparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    // Params from the UI:
    // private TextView textViewLoginTitle;
    // private ImageView imageViewLoginParkingLot;

    private TextInputLayout textViewLayoutUsername;
    private TextInputLayout textViewLayoutPassword;
    private Button buttonLogin;
    private TextView textViewCallSignUpIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewLayoutUsername = findViewById(R.id.textViewLayoutUsername);
        textViewLayoutPassword = findViewById(R.id.textViewLayoutPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewCallSignUpIntent = findViewById(R.id.textViewCallSignUpIntent);

        textViewCallSignUpIntent.setPaintFlags(textViewCallSignUpIntent.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        // route the user for the signup activity:
        textViewCallSignUpIntent.setOnClickListener((View view) -> {
            Intent signupIntent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(signupIntent);
        });

        // check database and route the user, if valid, to the user activity:
        buttonLogin.setOnClickListener((View view) -> {
            String username = textViewLayoutUsername.getEditText().getText().toString();
            String password = textViewLayoutPassword.getEditText().getText().toString();

            if(username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                return;
            }


            Intent userIntent = new Intent(LoginActivity.this, UserActivity.class);
            startActivity(userIntent);
        });
    }
}