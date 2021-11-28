package com.example.smartparking.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.smartparking.dao.UserDAO;
import com.example.smartparking.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class SmartParkingRoomDB extends RoomDatabase {
    // insert the dao's:
    public abstract UserDAO userDAO();



    /*
     * Singleton Pattern:
     * We try to ensure only one connection open and running in the background.
     */

    // Implementation for the Singleton pattern. (This db instance is going to be running in a background thread)
    public static final int NUMBER_OF_THREADS = 4;
    private static volatile SmartParkingRoomDB INSTANCE;

    // push to a background thread...
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SmartParkingRoomDB getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (SmartParkingRoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SmartParkingRoomDB.class, "smart_parking_database").addCallback(cb).build();
                }
            }
        }
        return INSTANCE;
    }

    //
    private static final RoomDatabase.Callback cb = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // populate in the background:
                UserDAO userDAO = INSTANCE.userDAO();
                userDAO.deleteAllUsers();

                // defualt data in the app for the first time we run:
                User user = new User("douglas@college.ca","123");
                userDAO.insertUser(user);
            });
        }
    };
}

