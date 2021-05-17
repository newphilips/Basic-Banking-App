package com.example.basicbankingapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.basicbankingapp.R;

public class MainActivity extends AppCompatActivity {

    Button mButtonShowAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonShowAccounts = findViewById(R.id.view_all_customers);

        mButtonShowAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllUsersActivity.class);
                startActivity(intent);
            }
        });
    }
}