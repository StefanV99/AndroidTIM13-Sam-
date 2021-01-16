package com.example.emailapplication.viewmodels;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.emailapplication.database.AppDatabase;
import com.example.emailapplication.entity.Contact;

import java.lang.ref.WeakReference;
import java.util.List;

public class ContactListViewModel extends AndroidViewModel {
    public MutableLiveData<List<Contact>> contactList;
    public AppDatabase appDatabase;

    public ContactListViewModel(@NonNull Application application) {
        super(application);
        this.appDatabase=AppDatabase.getInstance(application);
    }

    public LiveData<List<Contact>> getContacts(Long accId) {
        if (contactList == null) {
            contactList = new MutableLiveData<>();
            new ContactListViewModel.getCotactsListTask(ContactListViewModel.this,accId).execute();
        }
        return contactList;
    }

    private void setResult(List<Contact> contacts, int flag) {
        if(contacts!=null)
        {
            contactList.setValue(contacts);
        }
    }


    private static class getCotactsListTask extends AsyncTask<Void,Void,Boolean> {
        private WeakReference<ContactListViewModel> activityWeakReference;
        Long accId;
        private List<Contact> receivedContacts;

        getCotactsListTask(ContactListViewModel context, Long accountId){
            activityWeakReference=new WeakReference<>(context);
            this.accId=accountId;
        }


        @Override
        protected Boolean doInBackground(Void... voids) {

            receivedContacts=activityWeakReference.get().appDatabase.getContactDao().allContacts();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean.booleanValue())
                activityWeakReference.get().setResult(receivedContacts,1);
        }
    }

}
