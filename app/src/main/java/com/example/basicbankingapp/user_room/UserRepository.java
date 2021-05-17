package com.example.basicbankingapp.user_room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.basicbankingapp.data.User;
import com.example.basicbankingapp.user_room.UserDao;
import com.example.basicbankingapp.user_room.UserDatabase;

import java.util.List;

public class UserRepository {

    private static UserDatabase userDatabase;
    private static UserDao userDao;
    public LiveData<List<User>> allUsers;

    UserRepository(Application application) {
        userDatabase = UserDatabase.getInstance(application.getApplicationContext());
        userDao = userDatabase.userDao();
        allUsers = userDao.getAllUsers();
    }

    public void insertUser(User user) {
        new InsertAsyncTask(userDao).execute(user);
    }

    public void updateUser(User user) {
        new UpdateAsyncTask(userDao).execute(user);
    }


    public static class InsertAsyncTask extends AsyncTask<User, Void, Void> {

        UserDao mUserDao;

        InsertAsyncTask(UserDao userDao) {
            mUserDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    public static class UpdateAsyncTask extends AsyncTask<User, Void, Void> {

        UserDao mUserDao;

        UpdateAsyncTask(UserDao userDao) {
            mUserDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }
}

