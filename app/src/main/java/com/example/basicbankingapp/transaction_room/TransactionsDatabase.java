package com.example.basicbankingapp.transaction_room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.basicbankingapp.data.Transactions;
import com.example.basicbankingapp.transaction_room.TransactionDao;

@Database(entities = {Transactions.class}, version = 1)
public abstract class TransactionsDatabase extends RoomDatabase {

    private static TransactionsDatabase instance;

    public abstract TransactionDao transactionDao();

    public static synchronized TransactionsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TransactionsDatabase.class, "transactions_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

