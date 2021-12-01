package com.example.smartparking.dao;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.smartparking.model.Invoice;

import java.util.List;

@Dao
public interface InvoiceDAO {
    @Query("SELECT * FROM invoices")
    LiveData<List<Invoice>> getAllInvoices();

    @Query("SELECT * FROM invoices WHERE invoiceid=:invoiceid")
    Invoice getInvoice(int invoiceid);

    @Query("DELETE FROM invoices")
    void deleteAllInvoices();

    @Insert(onConflict = IGNORE)
    void insertInvoice(Invoice invoice);
}
