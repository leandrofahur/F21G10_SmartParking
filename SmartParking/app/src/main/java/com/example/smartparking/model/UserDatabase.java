package com.example.smartparking.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.smartparking.interfaces.UserDAO;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDAO getUserDAO();

}
