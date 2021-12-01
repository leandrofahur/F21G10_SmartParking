package com.example.smartparking.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartparking.R;
import com.example.smartparking.database.SmartParkingRoomDB;
import com.example.smartparking.dao.UserDAO;
import com.example.smartparking.model.User;
import com.example.smartparking.model.UserViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SignupActivity extends AppCompatActivity {

    private TextView textViewEmail;
    private TextView textViewPassword;
    private TextView textViewConfirmPassword;
    private TextView textViewCallSignInIntent;
    private Button buttonSignup;

    // DB:
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textViewEmail = findViewById(R.id.textViewEmail);
        textViewPassword = findViewById(R.id.textViewPassword);
        textViewConfirmPassword = findViewById(R.id.textViewConfirmPassword);
        buttonSignup = findViewById(R.id.buttonSignup);

        textViewCallSignInIntent = findViewById(R.id.textViewCallSignInIntent);

        textViewCallSignInIntent.setPaintFlags(textViewCallSignInIntent.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        // initialize the db instance and provide the abstraction to the userViewModel:
        userViewModel = new ViewModelProvider.AndroidViewModelFactory(SignupActivity.this.getApplication()).create(UserViewModel.class);

        textViewCallSignInIntent.setOnClickListener((View view) -> {
            Intent signInIntent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(signInIntent);
        });

        // check database and route the user, if valid, to the user activity:
        buttonSignup.setOnClickListener((View view) -> {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> {
                try {
                    String email = textViewEmail.getText().toString();
                    String password = textViewPassword.getText().toString();
                    String passwordConf = textViewConfirmPassword.getText().toString();

                    if (password.equals(passwordConf)) {
                        User user = new User(email, password);
                        UserViewModel.insertUser(user);

                        Intent profileIntent = new Intent(SignupActivity.this, ProfileActivity.class);

                        Bundle myBundle = new Bundle();
                        profileIntent.putExtra("Email",user.getEmail());
                        startActivity(profileIntent);
                        finish();

                    } else if (email.isEmpty() || password.isEmpty()){
                        Toast.makeText(this, "Fields can't be blank", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}