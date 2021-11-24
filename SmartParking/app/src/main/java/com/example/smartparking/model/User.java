package com.example.smartparking.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "users")

public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="userid")
    private int UserId;

    @ColumnInfo(name="username")
    private String UserName;

    @ColumnInfo(name="email")
    private String Email;

    @ColumnInfo(name="password")
    private String Password;

    @NonNull
    public int getUserId() {
        return UserId;
    }

    public void setUserId(@NonNull int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public User(String userName, String password) {
        UserName = userName;
        Password = password;
    }

    public User(String UserName, String Email, String Password) {
        this.UserName = UserName;
        this.Email = Email;
        this.Password = Password;
    }
}
