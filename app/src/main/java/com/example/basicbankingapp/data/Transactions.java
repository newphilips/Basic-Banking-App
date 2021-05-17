package com.example.basicbankingapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Time;

@Entity(tableName = "transactions_table")
public class Transactions {

    @PrimaryKey(autoGenerate = true)
    public int transaction_id;

    public String from;
    public String to;
    public int money;
    public String time;
    public int status;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getMoney() {
        return money;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Transactions( String from, String to, int money, String time, int status) {
        this.from = from;
        this.to = to;
        this.money = money;
        this.time = time;
        this.status = status;
    }
}
