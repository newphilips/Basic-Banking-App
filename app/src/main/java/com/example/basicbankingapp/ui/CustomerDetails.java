package com.example.basicbankingapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basicbankingapp.EnterAmountDialog;
import com.example.basicbankingapp.R;
import com.example.basicbankingapp.data.User;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomerDetails extends AppCompatActivity implements EnterAmountDialog.DialogAmountListener {

    TextView mUserName;
    TextView mUserPhone;
    TextView mUserAccount;
    TextView mUserEmail;
    TextView mUserBalance;
    CircleImageView mUserImage;
    Button mButtonTransfer;
    Toolbar mToolbar;

    Bundle bundle;
    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("user_bundle");
        mUser = bundle.getParcelable("user_parcel");

        mUserImage = findViewById(R.id.person_image);
        mToolbar = findViewById(R.id.detail_toolbar);
        mUserName = findViewById(R.id.person_name);
        mUserEmail = findViewById(R.id.person_email);
        mUserPhone = findViewById(R.id.person_phone);
        mUserAccount = findViewById(R.id.person_account);
        mUserBalance = findViewById(R.id.person_balance);
        mButtonTransfer = findViewById(R.id.button_transfer);

        setUpDetails();

        mButtonTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTransferDialog();
            }
        });
    }

    public void openTransferDialog(){
        EnterAmountDialog amountDialog = new EnterAmountDialog();
        amountDialog.show(getSupportFragmentManager(), "enter amount dialog");
    }

    public void setUpDetails(){
        mToolbar.setTitle(mUser.getUser_name());
        mUserName.setText(mUser.getUser_name());
        mUserEmail.setText(mUser.getUser_email());
        mUserPhone.setText(mUser.getUser_phone());
        mUserAccount.setText(mUser.getUser_account());
        mUserBalance.setText("Rs. " + mUser.getUser_balance() + "/-");
    }

    @Override
    public void sendMoney(int amount) {
        //start Selectuser activity///
        if(amount <= Integer.parseInt(mUser.getUser_balance())) {
            Intent intent = new Intent(CustomerDetails.this, SelectUser.class);
            Bundle bundle = new Bundle();

            bundle.putInt("from_user_money", amount);
            bundle.putParcelable("from_user", mUser);
            intent.putExtra("from_user_bundle", bundle);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Insufficient amount!!!", Toast.LENGTH_SHORT).show();
        }
    }
}