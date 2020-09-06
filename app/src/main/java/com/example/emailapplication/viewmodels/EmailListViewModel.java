package com.example.emailapplication.viewmodels;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.emailapplication.database.AppDatabase;
import com.example.emailapplication.entity.Account;
import com.example.emailapplication.entity.AccountMessages;
import com.example.emailapplication.entity.Message;

import java.lang.ref.WeakReference;
import java.util.List;

public class EmailListViewModel extends AndroidViewModel {

    public MutableLiveData<List<AccountMessages>> messages;
    public MutableLiveData<List<Message>> messagesList;
    public AppDatabase appDatabase;

    public EmailListViewModel(@NonNull Application application) {
        super(application);
        this.appDatabase=AppDatabase.getInstance(application);

    }
/*
    public LiveData<List<AccountMessages>> getMessages( Long accId) {

        if (messages == null) {
            messages = new MutableLiveData<List<AccountMessages>>();
            messages.setValue(appDatabase.getMessagesDao().findAccountMessages(accId));

            //    loadEmails(accId);
        }
        return messages;
    } */
    /*
    public LiveData<List<Message>> getMessageList( Long accId) {
        if (messagesList == null) {
            messagesList = new MutableLiveData<List<Message>>();
            messagesList.setValue(appDatabase.getMessagesDao().findAccountMessages(accId).get(0).messages);
            //    loadEmails(accId);
        }
        return messagesList;
    } */
public LiveData<List<Message>> getMessages(Long accId) {
    if (messagesList == null) {
        messagesList = new MutableLiveData<List<Message>>();
        new EmailListViewModel.getMessagesListTask(EmailListViewModel.this,accId).execute();
        //messagesList.setValue(appDatabase.getMessagesDao().findAccountMessages(accId).get(0).messages);
        //    loadEmails(accId);

    }
    return messagesList;
    /*if (accId != 0) {
        MutableLiveData<Account> acc=new MutableLiveData<Account>();
        //  acc.setValue(appDatabase.getAccountDao().findAccountById(accId.toString()));

        return account;
    }
    return  null;

     */

}

    private void setResult(List<Message> messages, int flag) {
        if(messages!=null)
        {
            messagesList.setValue(messages);
        }
    }


    private static class getMessagesListTask extends AsyncTask<Void,Void,Boolean> {
        private WeakReference<EmailListViewModel> activityWeakReference;
        Long accId;
        private List<Message> recievedMessages;

        getMessagesListTask(EmailListViewModel context,Long accountId){
            activityWeakReference=new WeakReference<>(context);
            this.accId=accountId;
        }


        @Override
        protected Boolean doInBackground(Void... voids) {
            recievedMessages=activityWeakReference.get().appDatabase.getMessagesDao().findAccountMessages(accId).get(0).messages;
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean.booleanValue()==true)
                activityWeakReference.get().setResult(recievedMessages,1);
        }
    }

}
