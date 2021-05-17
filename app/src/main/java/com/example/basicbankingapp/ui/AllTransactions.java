package com.example.basicbankingapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.adapter.TransactionsAdapter;
import com.example.basicbankingapp.data.Transactions;
import com.example.basicbankingapp.transaction_room.TransactionViewModel;

import java.util.ArrayList;
import java.util.List;


public class AllTransactions extends AppCompatActivity {

    RecyclerView mRecyclerView;
    TransactionsAdapter mAdapter;
    TransactionViewModel transactionViewModel;
    List<Transactions> list;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_transactions);

        list = new ArrayList<>();

        mToolbar = findViewById(R.id.transactions_toolbar);
        mRecyclerView = findViewById(R.id.transactions_recycelr_view);
        mAdapter = new TransactionsAdapter(this);

        mToolbar.setTitle("All Transactions");
        setSupportActionBar(mToolbar);

        transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
        transactionViewModel.getAllTransactions().observe(this, new Observer<List<Transactions>>() {
            @Override
            public void onChanged(List<Transactions> transactions) {
                list = transactions;
                mAdapter.setList(transactions);
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

    }
}