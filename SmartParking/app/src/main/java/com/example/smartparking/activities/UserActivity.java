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
        StatusList.add(new ClientStatusImage(01, "ParkedCar", R.drawable.parkedcar));
        StatusList.add(new ClientStatusImage(02, "Selected", R.drawable.selected));
        StatusList.add(new ClientStatusImage(03, "Free", R.drawable.free));
        StatusList.add(new ClientStatusImage(04, "Booked", R.drawable.booked));
        StatusList.add(new ClientStatusImage(05, "Car", R.drawable.car));
        StatusList.add(new ClientStatusImage(06, "Bike", R.drawable.bike));
        StatusList.add(new ClientStatusImage(07, "Van", R.drawable.van));
        StatusList.add(new ClientStatusImage(08, "Disabled", R.drawable.disabled));
    }
}