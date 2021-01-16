package com.example.emailapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.emailapplication.R;
import com.example.emailapplication.database.AppDatabase;
import com.example.emailapplication.entity.Contact;

import java.lang.ref.WeakReference;

public class CreateContactActivity extends AppCompatActivity  {
    EditText firstName;
    Intent intentCreateContactMessages;
    EditText lastName;
    EditText email;
    EditText display;
    AppDatabase appDatabase;
    String username;
    Long accId;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_contact_action_bar, menu);
        // return true so that the menu pop up is opened

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.create_contact_back) {
            intentCreateContactMessages= new Intent(getApplicationContext(), EmailsActivity.class);

            SharedPreferences sharedPreff=getSharedPreferences("currentUser", MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreff.edit();

          //  editor.putLong("userId",accId);
            //editor.putString("username" ,username);
            //editor.apply();
            startActivity(intentCreateContactMessages);
            Toast.makeText(CreateContactActivity.this,getString(R.string.unsaved_contact), Toast.LENGTH_LONG).show();
            finish();
            return true;
        }else if(id == R.id.create_contact_save){
            Contact contact=new Contact(firstName.getText().toString(),lastName.getText().toString(), email.getText().toString(),display.getText().toString(),true);
            Log.d("contact",contact.email+" "+contact.first+" "+contact.last+" "+contact.fomrat);
            new CreateContactActivity.createContact(this, contact).execute();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        Toolbar toolbar = findViewById(R.id.toolbar_create_contact);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreff = getSharedPreferences("currentUser", MODE_PRIVATE);
        username = sharedPreff.getString("username", "defaultStringIfNothingFound");
        accId = sharedPreff.getLong("userId", 1);
        firstName = findViewById(R.id.EditFirst);
        lastName = findViewById(R.id.EditLast);
        email = findViewById(R.id.EditEmail);
        display=findViewById(R.id.EditUsername);

    }
    private void setResult(Contact contactToCreate, int flag) {
        if(contactToCreate!=null)
        {
            Toast.makeText(CreateContactActivity.this,R.string.succesfullyCreatedContact,Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(CreateContactActivity.this,R.string.cannotCreateContact,Toast.LENGTH_LONG).show();
        }
    }



    private static class createContact extends AsyncTask<Void,Void,Boolean> {
        private WeakReference<CreateContactActivity> activityWeakReference;
        private Contact contactToCreate;

        createContact(CreateContactActivity context, Contact contact) {
            activityWeakReference = new WeakReference<>(context);
            this.contactToCreate = contact;
        }


        @Override
        protected Boolean doInBackground(Void... voids) {
            activityWeakReference.get().appDatabase.getContactDao().CreateContact(contactToCreate);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean.booleanValue())
                activityWeakReference.get().setResult(contactToCreate,1);
        }
    }

}
