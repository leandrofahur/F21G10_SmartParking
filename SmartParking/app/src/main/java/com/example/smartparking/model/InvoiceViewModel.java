package com.example.smartparking.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.smartparking.repository.InvoiceRepository;

import java.util.List;

public class InvoiceViewModel extends AndroidViewModel {
    public static InvoiceRepository invoiceRepository;
    public final LiveData<List<Invoice>> getAllInvoices;
    public Invoice getInvoice;

    public InvoiceViewModel(@NonNull Application application) {
        super(application);
        invoiceRepository = new InvoiceRepository(application);
        getAllInvoices = invoiceRepository.getAllData();
    }

    public LiveData<List<Invoice>> getAllInvoices() {
        return getAllInvoices;
    }

    public static void insertInvoice(Invoice invoice) { invoiceRepository.insert(invoice);}

    public static Invoice getInvoice(int invoiceId) { return invoiceRepository.getInvoice(invoiceId);}
}
