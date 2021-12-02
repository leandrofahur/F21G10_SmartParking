package com.example.smartparking.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.smartparking.database.SmartParkingRoomDB;
import com.example.smartparking.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    public static UserRepository userRepository;
    public final LiveData<List<User>> getAllUsers;
    public User user;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        getAllUsers = userRepository.getAllData();
    }

    public LiveData<List<User>> getAllUsers() {
        return getAllUsers;
    }

    public static void insertUser(User user) { userRepository.insert(user);}

    public static User getUser(String email, String password) { return userRepository.getUser(email, password);}

    public static User getUserByEmail(String email) { return userRepository.getUserByEmail(email);}

    public static void deleteUserByEmail(String email) { userRepository.deleteUserByEmail(email);}

    public static void deleteAllUsers() { userRepository.deleteAllUsers();}
}
