package com.example.basicbankingapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.TransferMoneyDialog;
import com.example.basicbankingapp.data.Transactions;
import com.example.basicbankingapp.transaction_room.TransactionViewModel;
import com.example.basicbankingapp.user_room.UserViewModel;
import com.example.basicbankingapp.adapter.AllUsersAdapter;
import com.example.basicbankingapp.data.User;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.SimpleTimeZone;


public class SelectUser extends AppCompatActivity implements AllUsersAdapter.UserClickedListener,
        TransferMoneyDialog.ConfirmTransferListener {

    Intent intent;
    Bundle bundle;
    RecyclerView mRecyclerView;
    AllUsersAdapter mAdapter;
    UserViewModel userViewModel;
    TransactionViewModel transactionViewModel;
    List<User> list;

    int to_user_id;
    int money;
    User from_user;
    User to_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        list = new ArrayList<>();
        intent = getIntent();
        bundle = intent.getBundleExtra("from_user_bundle");

        from_user = bundle.getParcelable("from_user");

        mRecyclerView = findViewById(R.id.users_recycler_view);
        mAdapter = new AllUsersAdapter(this);

        mAdapter.setmListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for(User thisUser : users) {
                    if(thisUser.user_id != from_user.getUser_id()) {
                        list.add(thisUser);
                    }
                    mAdapter.setList(list);
                }
            }
        });

    }

    @Override
    public void userClicked(int position) {

        to_user = list.get(position);

        String from_user_name = from_user.getUser_name();
        String to_user_name = to_user.getUser_name();
        int from_user_id = from_user.getUser_id();
        to_user_id = to_user.getUser_id();

        money = bundle.getInt("from_user_money");

        TransferMoneyDialog dialog = new TransferMoneyDialog();
        dialog.setDetails(from_user_name, to_user_name, from_user_id, money);
        dialog.show(getSupportFragmentManager(), "transfer money dialog");
    }

    @Override
    public void confirmTransfer(boolean isConfirmed) {

        String reduced_amount = String.valueOf(Integer.parseInt(from_user.getUser_balance()) - money);
        String added_amount = String.valueOf(Integer.parseInt(to_user.getUser_balance()) + money);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        if(isConfirmed){
            //make updations in database

            from_user.setUser_balance(reduced_amount);
            to_user.setUser_balance(added_amount);

            userViewModel.update(from_user);
            userViewModel.update(to_user);

            transactionViewModel.insert(

                    new Transactions(from_user.getUser_name(), to_user.getUser_name(), money, sdf.format(cal.getTime()), 1)
            );

            Bundle bundle = new Bundle();
            bundle.putInt("tran_money", money);
            bundle.putString("tran_to_user", to_user.getUser_name());

            Intent successfulIntent = new Intent(SelectUser.this, SuccessfulTransaction.class);
            successfulIntent.putExtra("tran_bundle", bundle);

            startActivity(successfulIntent);
            finish();

        } else {
            transactionViewModel.insert(
                    new Transactions(from_user.getUser_name(), to_user.getUser_name(), money, sdf.format(cal.getTime()), 0)
            );
            Toast.makeText(this, "You cancelled the transaction", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}