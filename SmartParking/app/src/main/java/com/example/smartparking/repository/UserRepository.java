package com.example.smartparking.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.smartparking.dao.UserDAO;
import com.example.smartparking.database.SmartParkingRoomDB;
import com.example.smartparking.model.User;

import java.util.List;

/*
 * The main advantage of using a repository, is to abstract form the user the roomDatabase, the Dao and etc...
 */
public class UserRepository {
    private UserDAO userDAO;
    private LiveData<List<User>> listLiveData;
    private User user;

    // the constructor opens the connection and makes the DAO available.
    // the lifecycle is bounded by the scope of the repository object.
    public UserRepository(Application application) {
        SmartParkingRoomDB db = SmartParkingRoomDB.getDatabase(application);
        userDAO = db.userDAO();
        listLiveData = userDAO.getAllUsers();
    }

    public LiveData<List<User>> getAllData() {
        return listLiveData;
    }

    public void insert(User user) {
        SmartParkingRoomDB.databaseWriteExecutor.execute(() -> {
            userDAO.insertUser(user);
        });
    }

    public User getUser(String email, String password) {
        SmartParkingRoomDB.databaseWriteExecutor.execute(() -> {
            user = userDAO.getUser(email, password);
        });
        return user;
    }

    public User getUserByEmail(String email) {
        SmartParkingRoomDB.databaseWriteExecutor.execute(() -> {
            user = userDAO.getUserByEmail(email);
        });
        return user;
    }

    public void deleteUserByEmail(String email) {
        SmartParkingRoomDB.databaseWriteExecutor.execute(() -> {
            userDAO.deleteUserByEmail(email);
        });
    }

    public void deleteAllUsers() {
        SmartParkingRoomDB.databaseWriteExecutor.execute(() -> {
            userDAO.deleteAllUsers();
        });
    }
}
