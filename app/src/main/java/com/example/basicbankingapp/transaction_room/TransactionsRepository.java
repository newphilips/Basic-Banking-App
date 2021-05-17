package com.example.basicbankingapp.transaction_room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.basicbankingapp.data.Transactions;

import java.util.List;

public class TransactionsRepository {

    private static TransactionsDatabase transactionsDatabase;
    private static TransactionDao transactionsDao;
    public LiveData<List<Transactions>> allTransactions;

    TransactionsRepository(Application application) {
        transactionsDatabase = TransactionsDatabase.getInstance(application.getApplicationContext());
        transactionsDao = transactionsDatabase.transactionDao();
        allTransactions = transactionsDao.getAllTransactions();
    }

    public void insertTransaction(Transactions transaction) {
        new InsertAsyncTask(transactionsDao).execute(transaction);
    }


    public static class InsertAsyncTask extends AsyncTask<Transactions, Void, Void> {

        TransactionDao mTransactionDao;

        InsertAsyncTask(TransactionDao transactionDao) {
            mTransactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(Transactions... transactions) {
            transactionsDao.insert(transactions[0]);
            return null;
        }
    }
}

