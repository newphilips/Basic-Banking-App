package com.example.basicbankingapp.user_room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.basicbankingapp.data.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase instance;

    public abstract UserDao userDao();

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, "users_database")
                    .addCallback(callback)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitializeDatabaseAsyncTask(instance).execute(
                    new User(1, "Aditya", "XYZ0000123", "aditya213@gmail.com", "998", "210"),
                    new User(2, "Arjun", "XYZ0000124", "arjun444@gmail.com", "898", "280"),
                    new User(3, "Akshit", "XYZ0000125", "akshit313@gmail.com", "788", "290"),
                    new User(4, "Aakash", "XYZ0000126", "aakash713@gmail.com", "958", "220"),
                    new User(5, "Raghav", "ABC0000126", "raghav2143@gmail.com", "919", "1000"),
                    new User(6, "Ritika", "ABC0000134", "ritika731@gmail.com", "991", "310"),
                    new User(7, "Rohan", "ABC0000432", "rohan713@gmail.com", "628", "2200"),
                    new User(8, "Sahil", "MNO0000523", "sahil723@gmail.com", "923", "220"),
                    new User(9, "Shivam", "MNO0000315", "shivam756@gmail.com", "964", "190"),
                    new User(10, "Aradhya", "XYZ0000314", "aradhya341@gmail.com", "923", "120")
            );
                    

        }
    };

    public static class InitializeDatabaseAsyncTask extends AsyncTask<User, Void, Void> {

        UserDao mUserDao;

        InitializeDatabaseAsyncTask(UserDatabase db) {
            mUserDao = db.userDao();
        }

        @Override
        protected Void doInBackground(User... user) {
            for(User thisUser : user){
                mUserDao.insert(thisUser);
            }
            return null;
        }
    }

}

