package com.example.smartparking.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartparking.R;
import com.example.smartparking.interfaces.UserDAO;
import com.example.smartparking.model.User;
import com.example.smartparking.model.UserDatabase;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    // Params from the UI:
    // private TextView textViewLoginTitle;
    // private ImageView imageViewLoginParkingLot;

    UserDAO db;
    UserDatabase dataBase;

    private TextView textViewLayoutUsername;
    private TextView textViewLayoutPassword;
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

        dataBase = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "users.db").allowMainThreadQueries().build();
        db = dataBase.getUserDAO();

        // check database and route the user, if valid, to the user activity:
        buttonLogin.setOnClickListener((View view) -> {
            String username = textViewLayoutUsername.getText().toString();
            String password = textViewLayoutPassword.getText().toString();

            User user = db.getUser(username, password);

            if(user != null) {
                Intent userIntent = new Intent (LoginActivity.this, UserActivity.class);
                userIntent.putExtra("User", user);
                startActivity(userIntent);
                finish();
            } else {
                Toast.makeText(this, "User not registered or Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        // route the user for the signup activity:
        textViewCallSignUpIntent.setOnClickListener((View view) -> {
            Intent signupIntent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(signupIntent);
        });
    }
}