package com.example.basicbankingapp.user_room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.basicbankingapp.data.User;
import com.example.basicbankingapp.user_room.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    public static UserRepository repository;
    public static LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.allUsers;
    }

    public void insert(User user) {
        repository.insertUser(user);
    }

    public void update(User user) {
        repository.updateUser(user);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
}
