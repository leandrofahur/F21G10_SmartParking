package com.example.smartparking.dao;

import static androidx.room.OnConflictStrategy.IGNORE;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.smartparking.model.User;

import java.util.List;

@Dao
public interface UserDAO {
    // Simple CRUD operation with a few extra methods:
    @Insert(onConflict = IGNORE)
    void insertUser(User user);

    @Query("DELETE FROM users")
    void deleteAllUsers();

    @Query("DELETE FROM users WHERE email=:email")
    void deleteUserByEmail(String email);

    @Query("SELECT * FROM users")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM users WHERE email=:email AND password=:password")
    User getUser(String email, String password);

    @Query("SELECT * FROM users WHERE email=:email")
    User getUserByEmail(String email);
}
