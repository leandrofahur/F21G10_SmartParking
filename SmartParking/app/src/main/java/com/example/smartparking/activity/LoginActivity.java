package com.example.smartparking.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    // Params from the UI:
    private TextInputLayout textViewLayoutEmail;
    private TextInputLayout textViewLayoutPassword;
    private Button buttonLogin;
    private TextView textViewCallSignUpIntent;

    // DB:
    private UserViewModel userViewModel;

    // firebase google sign-in options:
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 1024;
    private SignInButton buttonGoogleSignIn;
    private FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance();

        CreateGoogleSignInRequest();

        buttonGoogleSignIn = findViewById(R.id.buttonGoogleSignIn);

        buttonGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        // check database and route the user, if valid, to the user activity:
        buttonLogin.setOnClickListener((View view) -> {
            try {
                String email = textViewLayoutEmail.getEditText().getText().toString();
                String password = textViewLayoutPassword.getEditText().getText().toString();
                User user = userViewModel.getUser(email, password);
                    if(user != null) {
                        if(email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                            Intent profileIntent = new Intent(LoginActivity.this, ProfileActivity.class);
                            profileIntent.putExtra("Email",user.getEmail());
                            startActivity(profileIntent);
                            finish();
                        } else {
                            Toast.makeText(this, "User not registered or Invalid credentials", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(this, "Problem with the db, try it again!!!", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            mGoogleSignInClient.signOut();
        }

    }

    private void CreateGoogleSignInRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                            //if(UserViewModel.getUserByEmail(user.getEmail()) == null) {
                                UserViewModel.insertUser(new User(user.getEmail(), user.getIdToken(true).toString()));
                            //}

                            Intent profileIntent = new Intent(LoginActivity.this, ProfileActivity.class);

                            //Toast.makeText(LoginActivity.this, user.getDisplayName(), Toast.LENGTH_SHORT).show();

                            Bundle myBundle = new Bundle();
                            profileIntent.putExtra("Email",user.getEmail());
                            startActivity(profileIntent);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Google auth failed!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}