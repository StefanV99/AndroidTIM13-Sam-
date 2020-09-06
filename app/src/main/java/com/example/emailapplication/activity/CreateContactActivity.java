package com.example.emailapplication.activity;

import android.os.AsyncTask;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emailapplication.R;
import com.example.emailapplication.entity.Message;
import com.example.emailapplication.viewmodels.EmailListViewModel;

import java.lang.ref.WeakReference;
import java.util.List;

public class CreateContactActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.post_message:

        }

    }
    /*
    private static class createContact extends AsyncTask<Void,Void,Boolean> {
        private WeakReference<EmailListViewModel> activityWeakReference;
        Long accId;
        private List<Message> recievedMessages;

        createContact(EmailListViewModel context,Long accountId){
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
             //   activityWeakReference.get().setResult(recievedMessages,1);
        }
    } */
}
