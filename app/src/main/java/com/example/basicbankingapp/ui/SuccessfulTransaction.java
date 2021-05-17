package com.example.basicbankingapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.basicbankingapp.R;

public class SuccessfulTransaction extends AppCompatActivity {

    Intent intent;
    Bundle bundle;

    int money_transferred;
    String to_user;

    TextView transactionDetails;
    ImageView mImageSuccessful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_transaction);

        intent = getIntent();
        bundle = intent.getBundleExtra("tran_bundle");

        transactionDetails = findViewById(R.id.transaction_details);
        mImageSuccessful = findViewById(R.id.image_successful);

        money_transferred = bundle.getInt("tran_money");
        to_user = bundle.getString("tran_to_user");

        String details = "Money Rs. " + money_transferred + "/- sent to  " + to_user;
        Log.d("Success Transaction: ", "details");

        transactionDetails.setText(details);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SuccessfulTransaction.this, AllUsersActivity.class));
            }
        }, 3000);
    }

}