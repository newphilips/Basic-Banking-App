package com.example.basicbankingapp.transaction_room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.basicbankingapp.data.Transactions;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel {

    public static TransactionsRepository repository;
    public static LiveData<List<Transactions>> allTransactions;

    public TransactionViewModel(@NonNull Application application) {
        super(application);
        repository = new TransactionsRepository(application);
        allTransactions = repository.allTransactions;
    }

    public void insert(Transactions transaction) {
        repository.insertTransaction(transaction);
    }

    public LiveData<List<Transactions>> getAllTransactions() {
        return allTransactions;
    }
}
