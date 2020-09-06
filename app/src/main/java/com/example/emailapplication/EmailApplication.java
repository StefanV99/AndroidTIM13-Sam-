package com.example.emailapplication;

import android.app.Application;

import androidx.room.Room;

import com.example.emailapplication.database.AppDatabase;

public class EmailApplication extends Application {
    AppDatabase appDatabase=null;

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase= appDatabase.getInstance(this);//Room.databaseBuilder(this,AppDatabase.class,"app")
    }
}
