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
import androidx.appcompat.widget.Toolbar;

import com.example.emailapplication.R;
import com.example.emailapplication.database.AppDatabase;
import com.example.emailapplication.entity.Contact;
import com.example.emailapplication.entity.Message;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;

public class CreateMessageActivity extends AppCompatActivity implements View.OnClickListener {
AppDatabase appDatabase;
String username;
Long accId;
Button postMessage;
Long receiverId;
    EditText subject;
    EditText bccs ;
    EditText cccs ;
    EditText content ;
    Date currentTime ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
        appDatabase = AppDatabase.getInstance(CreateMessageActivity.this);
        //postMessage.setEnabled(false);
        SharedPreferences sharedPreff = getSharedPreferences("currentUser", MODE_PRIVATE);
        username = sharedPreff.getString("username", "defaultStringIfNothingFound");
        accId = sharedPreff.getLong("userId", 1);
        EditText sender = findViewById(R.id.your_username);
        sender.setText(username);
        Button sendBtn = (Button) findViewById(R.id.post_message);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText receiver = findViewById(R.id.receiver_email);
                 subject = findViewById(R.id.your_subject);
                 bccs = findViewById(R.id.bcc);
                 cccs = findViewById(R.id.ccc);
                 content = findViewById(R.id.your_message);
                 currentTime = Calendar.getInstance().getTime();

                //find the receiver and sender username and the bccs
                new CreateMessageActivity.getData(CreateMessageActivity.this, receiver.getText().toString()).execute();

            }
        });
        Toolbar backToMessagesToolbar = (Toolbar) findViewById(R.id.toolbarCreateMessageBackMessages);
        backToMessagesToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intentCreteMessagemessages = new Intent(CreateMessageActivity.this, EmailsActivity.class);
                startActivity(intentCreteMessagemessages);
                finish();
            }

        });
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
            Message messageToInput = new Message(accId, receiverId, currentTime, subject.getText().toString(), content.getText().toString());

            //get the data into the message from the view data
            new CreateMessageActivity.createMessage(CreateMessageActivity.this, messageToInput).execute();


        }else
        {
            TextView receiver= findViewById(R.id.receiver_email);
            receiver.setTextColor(Color.parseColor("#FF3F34"));

        }
    }

    @Override
    public void onClick(View view) {

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
            if (aBoolean.booleanValue())
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
            //activityWeakReference.get().appDatabase.getContactDao().deleteAllContacts();
            //Contact contact=new Contact("marko","markovic","marex","marex@t.com",false);
            //Long value=activityWeakReference.get().appDatabase.getContactDao().CreateContact(contact);
            //Log.d("CreatedContent", "doInBackground: "+value.toString());
            contactToSendTo=activityWeakReference.get().appDatabase.getContactDao().getContactByUsername(receiverEmail);

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean.booleanValue())
                activityWeakReference.get().setResultContact(contactToSendTo,1);
        }
    }
}
