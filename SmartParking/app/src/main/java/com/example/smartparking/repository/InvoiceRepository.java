package com.example.smartparking.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.smartparking.dao.InvoiceDAO;
import com.example.smartparking.database.SmartParkingRoomDB;
import com.example.smartparking.model.Invoice;

import java.util.List;

public class InvoiceRepository {
    private InvoiceDAO invoiceDAO;
    private LiveData<List<Invoice>> listLiveData;
    private Invoice invoice;

    public InvoiceRepository(Application application) {
        SmartParkingRoomDB db = SmartParkingRoomDB.getDatabase(application);
        invoiceDAO = db.invoiceDAO();
        listLiveData = invoiceDAO.getAllInvoices();
    }

    public LiveData<List<Invoice>> getAllData() {
        return listLiveData;
    }

    public void insert(Invoice invoice) {
        SmartParkingRoomDB.databaseWriteExecutor.execute(() -> invoiceDAO.insertInvoice(invoice));
    }

    public Invoice getInvoice (Integer invoiceId) {
        SmartParkingRoomDB.databaseWriteExecutor.execute(() -> invoice = invoiceDAO.getInvoice(invoiceId));
        return invoice;
    }
}
