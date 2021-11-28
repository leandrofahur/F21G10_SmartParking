package com.example.smartparking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
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
        userNameTextView = findViewById(R.id.txtViewNameUser);

        //add name (input) to textview after welcome textview

        parkingLotGridView = findViewById(R.id.gridViewParkingLot);
        selectCategoryTxtView = findViewById(R.id.txtViewSelectCategory);
        carsImgBtn = findViewById(R.id.imgBtnCars);
        bikeImgBtn = findViewById(R.id.imgBtnBike);
        vanImgBtn = findViewById(R.id.imgBtnVan);
        disabledImgBtn = findViewById(R.id.imgBtnDisabled);
        submitBtn = findViewById(R.id.btnSubmit);

        StatusImagesAdapter statusImagesAdapter = new StatusImagesAdapter(StatusList);
        parkingLotGridView.setAdapter(statusImagesAdapter);
        parkingLotGridView.setNumColumns(2);

        //gridview
        //on click listener, add toast to each image
        //on click listener for cars changing between free and selected
        //free-selected only once, if another one is clicked change previous back to free
        //padding adjustment (top & bottom)


        //image buttons
        //on click listener - toast of names for each type of vehicle
        //on click listener - change background to black & image color to yellow
        //padding adjustment


        //submit button
        //check if previous ones were checked
        //pop up screen to schedule time
    }

    private void AddDataList() {
        StatusList.add(new ClientStatusImage(01, "ParkedCar", R.drawable.parkedcar));
        StatusList.add(new ClientStatusImage(02, "Selected", R.drawable.selected));
        StatusList.add(new ClientStatusImage(03, "Free", R.drawable.free));
        StatusList.add(new ClientStatusImage(04, "Booked", R.drawable.booked));
    }
}