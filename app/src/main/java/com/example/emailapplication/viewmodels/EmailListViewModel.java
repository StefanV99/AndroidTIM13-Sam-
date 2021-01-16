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

public LiveData<List<Message>> getMessages(Long accId) {
    if (messagesList == null) {
        messagesList = new MutableLiveData<>();
        new EmailListViewModel.getMessagesListTask(EmailListViewModel.this,accId).execute();

    }
    return messagesList;
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
           // activityWeakReference.get().appDatabase.getMessagesDao().deleteAll();
            List<Message> allMessages=activityWeakReference .get().appDatabase.getMessagesDao().allMessages();
            recievedMessages=activityWeakReference.get().appDatabase.getMessagesDao().findAccountMessages(accId.toString());
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean.booleanValue())
                activityWeakReference.get().setResult(recievedMessages,1);
        }
    }

}
