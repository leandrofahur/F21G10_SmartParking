package com.example.smartparking.dao;

import static androidx.room.OnConflictStrategy.IGNORE;

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

    @Query("SELECT * FROM users WHERE email=:email and password=:password")
    LiveData<User> getUser(String email, String password);

    @Query("SELECT * FROM users")
    LiveData<List<User>> getAllUsers();
}
