package com.example.emailapplication.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.emailapplication.database.AppDatabase;
import com.example.emailapplication.entity.FolderMessages;
import com.example.emailapplication.helpEntities.Setting;

import java.util.List;

public class SettingsViewModel extends AndroidViewModel {
    AppDatabase appDatabase;
    public SettingsViewModel(@NonNull Application application) {
        super(application);
        this.appDatabase=AppDatabase.getInstance(application);
    }
    public Setting getSettings(Long accountId) {
        //refresh every one day
        Setting setting=new Setting(accountId,true,1);
        return setting;
    }
    public boolean setSettings(Long accountId) {
        //refresh every one day
        Setting setting=new Setting(accountId,true,1);
        //TODO:
        return true;
    }


}
