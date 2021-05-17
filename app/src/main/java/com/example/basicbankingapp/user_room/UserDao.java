package com.example.basicbankingapp.user_room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.basicbankingapp.data.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM users_table")
    LiveData<List<User>> getAllUsers();

}