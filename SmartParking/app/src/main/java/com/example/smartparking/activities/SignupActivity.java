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

public class SignupActivity extends AppCompatActivity {

    private TextView textViewUsernameEmail2;
    private TextView textViewPassword;
    private TextView textViewConfirmPassword;
    private TextView textViewCallSignInIntent;
    private Button buttonSignup;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textViewUsernameEmail2 = findViewById(R.id.textViewUsernameEmail2);
        textViewPassword = findViewById(R.id.textViewPassword);
        textViewConfirmPassword = findViewById(R.id.textViewConfirmPassword);
        buttonSignup = findViewById(R.id.buttonSignup);

        textViewCallSignInIntent = findViewById(R.id.textViewCallSignInIntent);

        textViewCallSignInIntent.setPaintFlags(textViewCallSignInIntent.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        textViewCallSignInIntent.setOnClickListener((View view) -> {
            Intent signInIntent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(signInIntent);
        });

        userDAO = Room.databaseBuilder(this, UserDatabase.class, "users.db").allowMainThreadQueries().build().userDAO();

        // check database and route the user, if valid, to the user activity:
        buttonSignup.setOnClickListener((View view) -> {
            String username = textViewUsernameEmail2.getText().toString();
            String password = textViewPassword.getText().toString();
            String passwordConf = textViewConfirmPassword.getText().toString();

            if (password.equals(passwordConf)) {
                User user = new User(username,password);
                userDAO.insertUser(user);
                Intent returnToSignIn = new Intent (SignupActivity.this, LoginActivity.class);
                startActivity(returnToSignIn);
                finish();
            } else if (username.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Fields can't be blank", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            }
        });
    }
}