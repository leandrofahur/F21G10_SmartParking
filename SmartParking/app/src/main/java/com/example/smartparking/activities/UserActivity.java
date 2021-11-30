package com.example.smartparking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartparking.R;
import com.example.smartparking.adapters.StatusImagesAdapter;
import com.example.smartparking.util.ClientStatusImage;

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

    private String control;
    private Toast currToast;
    private int clickedItemInd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        AddDataList();

        welcomeTxtView = findViewById(R.id.txtViewWelcome);
        userNameTextView = findViewById(R.id.txtViewNameUser);

        //add name (input) to textview after welcome textview - relying on ending of loginactivity

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
        parkingLotGridView.setOnItemClickListener((AdapterView<?> adapterView, View view, int i, long l) -> {

            currToast = Toast.makeText(UserActivity.this, StatusList.get(i).getStatusName(), Toast.LENGTH_LONG);
            currToast.show();

        });

        //on click listener for cars changing between free and selected


        //free-selected only once, if another one is clicked change previous clicked back to free
        //spacing adjustment (top & bottom) - still try to add borders
        GridView gridview = findViewById(R.id.gridViewParkingLot);
        gridview.setBackgroundColor(Color.parseColor("#F9F9F9"));
        gridview.setVerticalSpacing(60);
        gridview.setHorizontalSpacing(20);

        //image buttons

        //toast of names for each type of vehicle && change background to black and image color to yellow

        List<ImageButton> viewBtns = new ArrayList<>();
        viewBtns.add(carsImgBtn);
        viewBtns.add(bikeImgBtn);
        viewBtns.add(vanImgBtn);
        viewBtns.add(disabledImgBtn);

        List<Integer> originalImgBtn = new ArrayList<>();
        originalImgBtn.add(R.drawable.car);
        originalImgBtn.add(R.drawable.bike);
        originalImgBtn.add(R.drawable.van);
        originalImgBtn.add(R.drawable.disabled);

        carsImgBtn.setOnClickListener(view -> {

            for(int i = 0; i < viewBtns.size(); i++) {
                if(viewBtns.get(i).getId() == carsImgBtn.getId()) {
                    carsImgBtn.setImageResource(R.drawable.carclicked);
                    carsImgBtn.setBackgroundColor(getResources().getColor(R.color.gray_800));
                    currToast = Toast.makeText(UserActivity.this, "Car", Toast.LENGTH_LONG);
                    currToast.show();
                } else {
                    viewBtns.get(i).setBackgroundColor(getResources().getColor(R.color.gray_200));
                    viewBtns.get(i).setImageResource(originalImgBtn.get(i));
                }
            }
        });

        bikeImgBtn.setOnClickListener(view -> {

            for(int i = 0; i < viewBtns.size(); i++) {
                if(viewBtns.get(i).getId() == bikeImgBtn.getId()) {
                    bikeImgBtn.setImageResource(R.drawable.bikeclicked);
                    bikeImgBtn.setBackgroundColor(getResources().getColor(R.color.gray_800));
                    currToast = Toast.makeText(UserActivity.this, "Bike", Toast.LENGTH_LONG);
                    currToast.show();
                } else {
                    viewBtns.get(i).setBackgroundColor(getResources().getColor(R.color.gray_200));
                    viewBtns.get(i).setImageResource(originalImgBtn.get(i));
                }
            }
        });

        vanImgBtn.setOnClickListener(view -> {

            for(int i = 0; i < viewBtns.size(); i++) {
                if(viewBtns.get(i).getId() == vanImgBtn.getId()) {

                    vanImgBtn.setImageResource(R.drawable.vanclicked);
                    vanImgBtn.setBackgroundColor(getResources().getColor(R.color.gray_800));
                    currToast = Toast.makeText(UserActivity.this, "Van", Toast.LENGTH_LONG);
                    currToast.show();
                } else {
                    viewBtns.get(i).setBackgroundColor(getResources().getColor(R.color.gray_200));
                    viewBtns.get(i).setImageResource(originalImgBtn.get(i));
                }
            }
        });

        disabledImgBtn.setOnClickListener(view -> {

            for(int i = 0; i < viewBtns.size(); i++) {
                if(viewBtns.get(i).getId() == disabledImgBtn.getId()) {
                    disabledImgBtn.setImageResource(R.drawable.disabledclicked);
                    disabledImgBtn.setBackgroundColor(getResources().getColor(R.color.gray_800));
                    currToast = Toast.makeText(UserActivity.this, "Disabled", Toast.LENGTH_LONG);
                    currToast.show();
                } else {
                    viewBtns.get(i).setBackgroundColor(getResources().getColor(R.color.gray_200));
                    viewBtns.get(i).setImageResource(originalImgBtn.get(i));
                }
            }
        });


        //submit button

        //check if previous ones were checked
        //pop up screen to schedule time
    }

    private void AddDataList() {
        StatusList.add(new ClientStatusImage(1, "ParkedCar", R.drawable.parkedcar));
        StatusList.add(new ClientStatusImage(2, "Selected", R.drawable.selected));
        StatusList.add(new ClientStatusImage(3, "Free", R.drawable.free));
        StatusList.add(new ClientStatusImage(4, "Booked", R.drawable.booked));
        StatusList.add(new ClientStatusImage(5, "Empty", R.drawable.emptyspot));
        StatusList.add(new ClientStatusImage(6, "Empty", R.drawable.emptyspot));
        StatusList.add(new ClientStatusImage(7, "ParkedCar", R.drawable.parkedcar));
        StatusList.add(new ClientStatusImage(8, "Selected", R.drawable.selected));
        StatusList.add(new ClientStatusImage(9, "Free", R.drawable.free));
        StatusList.add(new ClientStatusImage(10, "Booked", R.drawable.booked));
    }
}