package com.example.emailapplication.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.emailapplication.database.AppDatabase;
import com.example.emailapplication.entity.AccountMessages;
import com.example.emailapplication.entity.FolderMessages;
import com.example.emailapplication.entity.Message;

import java.util.List;

public class FoldersViewModel extends AndroidViewModel {
    AppDatabase appDatabase;
    public MutableLiveData<List<FolderMessages>> folderMessages;
    public MutableLiveData<List<Message>> messagesList;
    public FoldersViewModel(@NonNull Application application) {
        super(application);
        this.appDatabase=AppDatabase.getInstance(application);

    }

    public LiveData<List<FolderMessages>> getMessages(Long folderId) {

        if (folderMessages == null) {
            folderMessages = new MutableLiveData<List<FolderMessages>>();
            folderMessages.setValue(appDatabase.getMessagesDao().findFolderMessages(folderId));

            //    loadEmails(accId);
        }
        return folderMessages;
    }
    public LiveData<List<Message>> getMessageList( Long folderId) {
        if (messagesList == null) {
            messagesList = new MutableLiveData<List<Message>>();
            messagesList.setValue(appDatabase.getMessagesDao().findFolderMessages(folderId).get(0).messages);
            //folder.setValue(appDatabase.getMessagesDao().findFolderMessages(folderId).get(0).folder);
            //    loadEmails(accId);
        }
        return messagesList;
    }

}
