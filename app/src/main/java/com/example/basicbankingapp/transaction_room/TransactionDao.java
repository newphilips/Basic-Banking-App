package com.example.basicbankingapp.transaction_room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.basicbankingapp.data.Transactions;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert
    void insert(Transactions transaction);

    @Query("SELECT * FROM transactions_table")
    LiveData<List<Transactions>> getAllTransactions();
}
