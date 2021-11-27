package com.example.smartparking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.smartparking.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    List<ClientStatusImage> StatusList = new ArrayList<>(); //empty list

    private TextView welcomeTxtView;
    private TextView userNameTextView;
    private TextView inputText;
    private GridView parkingLotGridView;
    private TextView selectCategoryTxtView;
    private ImageButton carsImgBtn;
    private ImageButton bikeImgBtn;
    private ImageButton vanImgBtn;
    private ImageButton disabledImgBtn;
    private Button submitBtn;

    private int clickedItemInd = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        AddDataList();

        welcomeTxtView = findViewById(R.id.txtViewWelcome);
        userNameTextView = findViewById(R.id.txtViewNameUser); //get username from input on login to set text in user name textview

        inputText = findViewById(R.id.textViewLayoutUsername);
        userNameTextView.setText(inputText.getText());

        parkingLotGridView = findViewById(R.id.gridViewParkingLot);
        selectCategoryTxtView = findViewById(R.id.txtViewSelectCategory);
        carsImgBtn = findViewById(R.id.imgBtnCars);
        bikeImgBtn = findViewById(R.id.imgBtnBike);
        vanImgBtn = findViewById(R.id.imgBtnVan);
        disabledImgBtn = findViewById(R.id.imgBtnDisabled);
        submitBtn = findViewById(R.id.btnSubmit);


    }

    private void AddDataList() {
        StatusList.add(new ClientStatusImage(1, "ParkedCar", R.drawable.parkedcar));
        StatusList.add(new ClientStatusImage(2, "Selected", R.drawable.selected));
        StatusList.add(new ClientStatusImage(3, "Free", R.drawable.free));
        StatusList.add(new ClientStatusImage(4, "Booked", R.drawable.booked));
        StatusList.add(new ClientStatusImage(5, "Car", R.drawable.car));
        StatusList.add(new ClientStatusImage(6, "Bike", R.drawable.bike));
        StatusList.add(new ClientStatusImage(7, "Van", R.drawable.van));
        StatusList.add(new ClientStatusImage(8, "Disabled", R.drawable.disabled));
    }
}