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

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        getAllUsers = userRepository.getAllData();
    }

    public LiveData<List<User>> getAllUsers() {
        return getAllUsers;
    }

    public static void insertUser(User user) { userRepository.insert(user);}

}
