package com.example.emailapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.emailapplication.R;
import com.example.emailapplication.database.AppDatabase;
import com.example.emailapplication.entity.Contact;
import com.example.emailapplication.entity.Message;
import com.example.emailapplication.viewmodels.EmailListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;

public class CreateMessageActivity extends AppCompatActivity implements View.OnClickListener {
AppDatabase appDatabase;
String username;
Long accId;
Button postMessage;
Long receiverId;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
        appDatabase=AppDatabase.getInstance(CreateMessageActivity.this);
        postMessage = findViewById(R.id.post_message);
        //postMessage.setEnabled(false);
        SharedPreferences sharedPreff=getSharedPreferences("currentUser", MODE_PRIVATE);
        username=sharedPreff.getString("username","defaultStringIfNothingFound");
        accId=sharedPreff.getLong("userId",1);
        EditText sender= findViewById(R.id.your_username);
        sender.setText(username);
        Button sendBtn = (Button) findViewById(R.id.post_message);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText receiver= findViewById(R.id.receiver_email);
                EditText subject= findViewById(R.id.your_subject);
                EditText bccs= findViewById(R.id.bcc);
                EditText cccs= findViewById(R.id.ccc);
                EditText content= findViewById(R.id.your_message);
                //find the receiver and sender username and the bccs
                new CreateMessageActivity.getData(CreateMessageActivity.this,receiver.getText().toString()).execute();
                Date currentTime = Calendar.getInstance().getTime();

                Message messageToInput=new Message(accId,receiverId,currentTime,subject.getText().toString(),content.getText().toString() );

                //get the data into the message from the view data
                new CreateMessageActivity.createMessage(CreateMessageActivity.this,messageToInput).execute();
            }});

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.toolbarBackToMessages:
              Intent  intentCreteMessagemessages = new Intent(this, MessagesActivity.class);
                startActivity(intentCreteMessagemessages);
                finish();
                break;
            default:
                break;
        }


    }
    private void setResult(Message message, int flag) {
        if(message!=null)
        {
            Toast.makeText(CreateMessageActivity.this,R.string.succesfullSent,Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(CreateMessageActivity.this,R.string.cannotSend,Toast.LENGTH_LONG).show();
        }
    }


    private void setResultContact(Contact setResultContact, int flag) {
        if(setResultContact!=null)
        {
            receiverId=setResultContact.id;
        }else
        {
            TextView receiver= findViewById(R.id.receiver_email);
            receiver.setTextColor(Color.parseColor("#FF3F34"));

        }
    }
    private static class createMessage extends AsyncTask<Void,Void,Boolean> {
        private WeakReference<CreateMessageActivity> activityWeakReference;
        Long accId;
        private Message sentMessage;

        createMessage(CreateMessageActivity context, Message message) {
            activityWeakReference = new WeakReference<>(context);
            this.sentMessage = message;
        }


        @Override
        protected Boolean doInBackground(Void... voids) {
            activityWeakReference.get().appDatabase.getMessagesDao().insertMessage(sentMessage);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean.booleanValue() == true)
               activityWeakReference.get().setResult(sentMessage,1);
        }
    }
    private static class getData extends AsyncTask<Void,Void,Boolean> {
        private WeakReference<CreateMessageActivity> activityWeakReference;
        Contact contactToSendTo;
        private String receiverEmail;

        getData(CreateMessageActivity context, String receiverEmail) {
            activityWeakReference = new WeakReference<>(context);
            this.receiverEmail = receiverEmail;
        }


        @Override
        protected Boolean doInBackground(Void... voids) {
            Contact contact=new Contact("marko","markovic","marex","marex@t.com",false);
            activityWeakReference.get().appDatabase.getContactDao().CreateContact(contact);
            contactToSendTo=activityWeakReference.get().appDatabase.getContactDao().getContactByUsername(receiverEmail);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean.booleanValue() == true)
                activityWeakReference.get().setResultContact(contactToSendTo,1);
        }
    }
}
