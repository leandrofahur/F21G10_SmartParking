package com.example.smartparking.interfaces;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.smartparking.model.User;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM users WHERE email = :Email and password = :Password")
    User getUser(String Email, String Password);

    @Insert(onConflict = IGNORE)
    void insertUser(User user);
}
