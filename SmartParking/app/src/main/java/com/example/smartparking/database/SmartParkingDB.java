package com.example.smartparking.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.smartparking.dao.UserDAO;
import com.example.smartparking.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class SmartParkingDB extends RoomDatabase {
    public abstract UserDAO userDAO();

    /*
     * TODO: Review implementation for Singleton pattern:
    // Implementation for the Singleton pattern. (This db instance is going to be running in a background thread)
    private static final int NUMBER_OF_THREADS = 4;
    private static volatile SmartParkingDB INSTANCE;

    // push to a background thread...
    private static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SmartParkingDB getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (SmartParkingDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SmartParkingDB.class, "smart_parking_database").build();
                }
            }
        }
        return INSTANCE;
    }
     */
}

