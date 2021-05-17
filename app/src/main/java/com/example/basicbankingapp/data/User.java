package com.example.basicbankingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users_table")
public class User implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int user_id;

    public String user_name;
    public String user_account;
    public String user_email;
    public String user_phone;
    public String user_balance;

    public User() {
    }

    public User(int user_id, String user_name, String user_account, String user_email, String user_phone, String user_balance) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_account = user_account;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.user_balance = user_balance;
    }

    protected User(Parcel in) {
        user_id = in.readInt();
        user_name = in.readString();
        user_account = in.readString();
        user_email = in.readString();
        user_phone = in.readString();
        user_balance = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_balance() {
        return user_balance;
    }

    public void setUser_balance(String user_balance) {
        this.user_balance = user_balance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(user_id);
        parcel.writeString(user_name);
        parcel.writeString(user_account);
        parcel.writeString(user_email);
        parcel.writeString(user_phone);
        parcel.writeString(user_balance);
    }
}
