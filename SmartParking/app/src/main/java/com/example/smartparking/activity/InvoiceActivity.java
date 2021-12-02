package com.example.smartparking.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartparking.R;
import com.example.smartparking.model.Invoice;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InvoiceActivity extends AppCompatActivity {
    EditText editTextDate;
    TextView txtViewHoursOfStay;
    private Button btnGenerateInvoice;
    private double duration;
    private double totalCost;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.CANADA);

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        editTextDate = findViewById(R.id.editTextDate);
        txtViewHoursOfStay = findViewById(R.id.txtViewHoursOfStay);
        btnGenerateInvoice = findViewById(R.id.btnGenerateInvoice);

        btnGenerateInvoice.setOnClickListener((View view) -> {
            generatePDF();
        });
    }

    public void takePermissions (View view){
        if (isPermissionGranted()) {
            Toast.makeText(this, "Permissions already granted", Toast.LENGTH_SHORT).show();
        } else {
            takePermission();
        }
    }

    private boolean isPermissionGranted(){
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R){
            return Environment.isExternalStorageManager();
        }
        else {
            int readExternalStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            return readExternalStoragePermission == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void takePermission(){
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent (Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
                startActivityForResult(intent, 100);
            } catch (Exception ex){
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 100);
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == 100) {
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
                    if (Environment.isExternalStorageManager()){
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    } else {
                        takePermission();
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            if (requestCode == 101) {
                boolean readExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (readExternalStorage) {
                    Toast.makeText(this, "Read Permission is Granted in Android 10 or below", Toast.LENGTH_SHORT).show();
                } else {
                    takePermission();
                }
            }
        }
    }

    //method to generate invoice PDF
    @RequiresApi(api = Build.VERSION_CODES.R)
    private void generatePDF(){
        PdfDocument myInvoice = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 300, 1).create();
        PdfDocument.Page myPage = myInvoice.startPage(pageInfo);

        duration = Double.parseDouble(txtViewHoursOfStay.getText().toString());

        Invoice invoice = new Invoice();
        invoice.setDuration(duration);
        totalCost = invoice.getTotalCost();

        Paint paint = new Paint();
        Paint forLinePaint = new Paint();

        Canvas canvas = myPage.getCanvas();

        paint.setTextSize(15.5f);
        paint.setColor(Color.YELLOW);
        canvas.drawText("SMART PARKING", 20, 20, paint);

        paint.setTextSize(8.5f);
        paint.setColor(Color.BLACK);
        canvas.drawText("Thanks for using SMART PARKING today!!", 20, 55, paint);
        canvas.drawText(editTextDate.getText().toString(), 20, 65, paint);
        canvas.drawText("Length of stay: " + txtViewHoursOfStay.getText().toString() + " hours.", 20, 75, paint);

        canvas.drawText("Payment Break-up", 20, 95, paint);
        forLinePaint.setStyle(Paint.Style.STROKE);
        forLinePaint.setPathEffect(new DashPathEffect(new float[]{5,5},0));
        forLinePaint.setStrokeWidth(2);
        canvas.drawLine(20, 105, 230, 105, forLinePaint);

        canvas.drawText("Total: $" + String.format("%,.2f", totalCost), 20, 115, paint);
        //TODO: Fix String.format for currency
        canvas.drawLine(20, 125, 230, 125, forLinePaint);

        //canvas.drawText(String.valueOf(decimalFormat.format(totalCost)), 230, 105, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Best Regards,", 20, 140, paint);
        canvas.drawText("SMART PARKING TEAM", 20, 150, paint);

        myInvoice.finishPage(myPage);

        String myFilePath = Environment.getExternalStorageDirectory().getPath() + "/Invoice.pdf";
        File invoicePDF = new File(myFilePath);

        try{
            myInvoice.writeTo(new FileOutputStream(invoicePDF));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        myInvoice.close();
    }
}