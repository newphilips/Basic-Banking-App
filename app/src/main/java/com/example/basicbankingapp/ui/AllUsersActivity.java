package com.example.basicbankingapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.user_room.UserViewModel;
import com.example.basicbankingapp.adapter.AllUsersAdapter;
import com.example.basicbankingapp.data.User;

import java.util.ArrayList;
import java.util.List;


public class AllUsersActivity extends AppCompatActivity implements AllUsersAdapter.UserClickedListener {

    RecyclerView mRecyclerView;
    AllUsersAdapter mAdapter;
    UserViewModel userViewModel;
    List<User> list;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        list = new ArrayList<>();

        mToolbar = findViewById(R.id.all_users_toolbar);
        mRecyclerView = findViewById(R.id.users_recycler_view);
        mAdapter = new AllUsersAdapter(this);

        mToolbar.setTitle("All Users");
        setSupportActionBar(mToolbar);
        mAdapter.setmListener(this);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                list = users;
                mAdapter.setList(users);
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.all_users_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case (R.id.menu_transactions):
                Intent allTransactions = new Intent(AllUsersActivity.this, AllTransactions.class);
                startActivity(allTransactions);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void userClicked(int position) {
        Intent intent = new Intent(AllUsersActivity.this, CustomerDetails.class);
        Bundle bundle = new Bundle();
        User clickedUser = list.get(position);
        bundle.putParcelable("user_parcel", clickedUser);
        intent.putExtra("user_bundle", bundle);
        startActivity(intent);
        //Toast.makeText(this, "User with name: "+list.get(position).getUser_name(), Toast.LENGTH_SHORT).show();
    }
}