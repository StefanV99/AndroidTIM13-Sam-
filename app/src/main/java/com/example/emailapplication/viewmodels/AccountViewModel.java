package com.example.emailapplication.viewmodels;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.emailapplication.activity.LoginActivity;
import com.example.emailapplication.database.AppDatabase;
import com.example.emailapplication.entity.Account;
import com.example.emailapplication.entity.AccountMessages;

import java.lang.ref.WeakReference;
import java.util.List;

public class AccountViewModel  extends AndroidViewModel {
    AppDatabase appDatabase;
    MutableLiveData<Account> account;
    public AccountViewModel(@NonNull Application application) {
        super(application);
        this.appDatabase=AppDatabase.getInstance(application);
    }
    public LiveData<Account> getAccountInfo(Long accId) {

        if (accId != 0) {
            MutableLiveData<Account> acc=new MutableLiveData<Account>();
             //  acc.setValue(appDatabase.getAccountDao().findAccountById(accId.toString()));
            new AccountViewModel.getAccountTask(AccountViewModel.this,accId).execute();
            return account;
        }
            return  null;
    }
    private void setResult(Account account1,int flag) {
        if(account1!=null)
        {
            account.setValue(account1);
        }
    }

    private static class getAccountTask extends AsyncTask<Void,Void,Boolean> {
        private WeakReference<AccountViewModel> activityWeakReference;
        Long accId;
        private Account recievedAccount;

        getAccountTask(AccountViewModel context,Long accountId){
            activityWeakReference=new WeakReference<>(context);
            this.accId=accountId;
        }


        @Override
        protected Boolean doInBackground(Void... voids) {
            recievedAccount=activityWeakReference.get().appDatabase.getAccountDao().findAccountById(this.accId.toString());
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean.booleanValue()==true)
                activityWeakReference.get().setResult(recievedAccount,1);
        }
    }


}
