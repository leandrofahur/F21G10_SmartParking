package com.example.smartparking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartparking.R;
import com.example.smartparking.adapters.StatusImagesAdapter;
import com.example.smartparking.util.ClientStatusImage;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    List<ClientStatusImage> StatusList = new ArrayList<>(); //empty list

    private TextView welcomeTxtView;
    private TextView userNameTextView;
    private TextInputLayout inputText;
    private GridView parkingLotGridView;
    private TextView selectCategoryTxtView;
    private ImageButton carsImgBtn;
    private ImageButton bikeImgBtn;
    private ImageButton vanImgBtn;
    private Button submitBtn;
    private Toast currToast;
    int currentPosition = -1;
    int ctrl = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        AddDataList();

        welcomeTxtView = findViewById(R.id.txtViewWelcome);
        userNameTextView = findViewById(R.id.txtViewNameUser);

        userNameTextView.setText(getIntent().getExtras().getString("Email"));

        parkingLotGridView = findViewById(R.id.gridViewParkingLot);
        selectCategoryTxtView = findViewById(R.id.txtViewSelectCategory);
        carsImgBtn = findViewById(R.id.imgBtnCars);
        bikeImgBtn = findViewById(R.id.imgBtnBike);
        vanImgBtn = findViewById(R.id.imgBtnVan);
        submitBtn = findViewById(R.id.btnSubmit);

        StatusImagesAdapter statusImagesAdapter = new StatusImagesAdapter(StatusList);
        parkingLotGridView.setAdapter(statusImagesAdapter);
        parkingLotGridView.setNumColumns(2);


        //gridview

        //on click listener, add toast to each image && for cars changing between free and selected
        parkingLotGridView.setOnItemClickListener((AdapterView<?> adapterView, View view, int i, long l) -> {
            ImageView imageView = (ImageView) view;

            switch (StatusList.get(i).getStatusName()) {
                case "Free":
                    StatusList.get(i).setStatusName("Selected");
                    imageView.setImageResource(R.drawable.selected);
                    break;

                case "Selected":
                    StatusList.get(i).setStatusName("Free");
                    imageView.setImageResource(R.drawable.free);
                    break;

                default:
                    break;
            }

        });

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

        List<Integer> originalImgBtn = new ArrayList<>();
        originalImgBtn.add(R.drawable.car);
        originalImgBtn.add(R.drawable.bike);
        originalImgBtn.add(R.drawable.van);

        carsImgBtn.setOnClickListener(view -> {

            for(int i = 0; i < viewBtns.size(); i++) {
                if(viewBtns.get(i).getId() == carsImgBtn.getId()) {
                    carsImgBtn.setImageResource(R.drawable.carclicked);
                    carsImgBtn.setBackgroundColor(getResources().getColor(R.color.gray_800));

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

                } else {
                    viewBtns.get(i).setBackgroundColor(getResources().getColor(R.color.gray_200));
                    viewBtns.get(i).setImageResource(originalImgBtn.get(i));
                }
            }
        });

        submitBtn.setOnClickListener(view -> {
            if (!parkingLotGridView.isSelected()) {
                Toast.makeText(UserActivity.this, "Please select a spot", Toast.LENGTH_LONG).show();
            } else if (!carsImgBtn.isSelected()|| !bikeImgBtn.isPressed() || !vanImgBtn.isPressed()) {
                 Toast.makeText(UserActivity.this, "Please select a type of vehicle", Toast.LENGTH_LONG).show();

            } else {
                //TODO: pop up screen to schedule time && send invoice
            }
        });
    }

    private void AddDataList() {
        StatusList.add(new ClientStatusImage(1, "ParkedCar", R.drawable.parkedcar));
        StatusList.add(new ClientStatusImage(2, "Free", R.drawable.free));
        StatusList.add(new ClientStatusImage(3, "Free", R.drawable.free));
        StatusList.add(new ClientStatusImage(4, "Booked", R.drawable.booked));
        StatusList.add(new ClientStatusImage(5, "Empty", R.drawable.emptyspot));
        StatusList.add(new ClientStatusImage(6, "Empty", R.drawable.emptyspot));
        StatusList.add(new ClientStatusImage(7, "Booked", R.drawable.booked));
        StatusList.add(new ClientStatusImage(8, "Free", R.drawable.free));
        StatusList.add(new ClientStatusImage(9, "Free", R.drawable.free));
        StatusList.add(new ClientStatusImage(10, "ParkedCar", R.drawable.parkedcarsidetwo));
    }
}