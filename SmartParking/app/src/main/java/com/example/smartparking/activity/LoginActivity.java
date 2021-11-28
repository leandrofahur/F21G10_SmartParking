package com.example.smartparking.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartparking.R;
import com.example.smartparking.database.SmartParkingRoomDB;
import com.example.smartparking.dao.UserDAO;
import com.example.smartparking.model.User;
import com.example.smartparking.model.UserViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    // Params from the UI:
    private TextInputLayout textViewLayoutEmail;
    private TextInputLayout textViewLayoutPassword;
    private Button buttonLogin;
    private TextView textViewCallSignUpIntent;

    // DB:
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewLayoutEmail = findViewById(R.id.textViewLayoutEmail);
        textViewLayoutPassword = findViewById(R.id.textViewLayoutPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        textViewCallSignUpIntent = findViewById(R.id.textViewCallSignUpIntent);
        textViewCallSignUpIntent.setPaintFlags(textViewCallSignUpIntent.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        // initialize the db instance and provide the abstraction to the userViewModel:
        userViewModel = new ViewModelProvider.AndroidViewModelFactory(LoginActivity.this.getApplication()).create(UserViewModel.class);

        // observe changes on the insertion of users in the database:
        userViewModel.getAllUsers().observe(this, users -> {
            Log.d("DB_DEBUG", "Users: " + userViewModel.getAllUsers());
        });


        // check database and route the user, if valid, to the user activity:
        buttonLogin.setOnClickListener((View view) -> {
            try {
                String email = textViewLayoutEmail.getEditText().getText().toString();
                String password = textViewLayoutPassword.getEditText().getText().toString();

                User user = userViewModel.getUser(email, password);

                    if(user != null) {
                    if(email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                        Intent userIntent = new Intent(LoginActivity.this, UserActivity.class);
                        //userIntent.putExtra("User", user.getUserName());
                        startActivity(userIntent);
                        finish();
                    }
                    } else {
                        Toast.makeText(this, "User not registered or Invalid credentials", Toast.LENGTH_SHORT).show();
                    }

            } catch (Exception ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // route the user for the signup activity:
        textViewCallSignUpIntent.setOnClickListener((View view) -> {
            Intent signupIntent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(signupIntent);
        });
    }
}