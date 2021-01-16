package com.example.emailapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.emailapplication.R;
import com.example.emailapplication.database.AppDatabase;
import com.example.emailapplication.entity.Account;


import java.lang.ref.WeakReference;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private Button register;
    private Toolbar back;
    private EditText password,username,pop3Imap,smtp;
    Intent intentRegisterLogin,intentRegisterMessages;
    //private HTTPHelper mHTTPHelper;
    private Handler mHandler;
    AppDatabase appDatabase;

    TextWatcher TxtWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Check();
        }
        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
    void Check() {
        if ((password.length() >6) && (username.getText().toString().isEmpty()!=true)) {
            register.setEnabled(true);

        } else {
            register.setEnabled(false);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        appDatabase=AppDatabase.getInstance(RegisterActivity.this);

        username = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        pop3Imap=findViewById(R.id.registerPop3);
        smtp=findViewById(R.id.registerSmtp);

        register = findViewById(R.id.registerBtn);
        back=findViewById(R.id.toolbarBack);
        register.setEnabled(false);
        username.addTextChangedListener(TxtWatcher);
        password.addTextChangedListener(TxtWatcher);

        register.setOnClickListener(this);
        back.setOnClickListener(this);
        //database=new DbHelper(this);
        //mHTTPHelper=new HTTPHelper();
        mHandler=new Handler();


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.registerBtn):
               Account account=new Account(username.getText().toString(),password.getText().toString(), Integer.parseInt(smtp.getText().toString()),Integer.parseInt(pop3Imap.getText().toString()));
                Log.d("creating account", "account creation");
               new RegisterTask(RegisterActivity.this,account).execute();

                Log.d("account created", "account created");
                /*intentRegisterMessages = new Intent(this, MessagesActivity.class);
                Log.d("creating account", "account creation");
                Long id= mainRepo.insertAccount(account);//appDatabase.accountDao().createAccount(account); //get contact id
                Log.d("account created", "account created with id: "+id);
                startActivity(intentRegisterMessages);
                finish(); */
                break;

            case (R.id.toolbarBack):
                intentRegisterLogin = new Intent(this, LoginActivity.class);
                startActivity(intentRegisterLogin);
                finish();
                break;
    }
}
    private void setResult(Account acc,int flag){
        Log.d("account", "account "+flag);
        if(acc==null)
        {
            Toast.makeText(getApplicationContext(), R.string.cannotRegister, Toast.LENGTH_LONG).show();
        }
        else {
            Log.d("account", "account created for real");
            Toast.makeText(RegisterActivity.this,getString(R.string.registerSucces),Toast.LENGTH_LONG).show();


            /*intentLoginMessages= new Intent(getApplicationContext(), MessagesActivity.class);

            //change the preferences with editor

            SharedPreferences sharedPreff=getSharedPreferences("currentUser", MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreff.edit();

            editor.putLong("userId",acc.getId());
            editor.apply();
            startActivity(intentLoginMessages);
            // setResult(flag,new Intent().putExtra("account",acc));
            finish(); */

        }
    }
    private static class RegisterTask extends AsyncTask<Void,Void,Boolean> {
        private WeakReference<RegisterActivity> activityWeakReference;
        private Account accountToInput;


        RegisterTask(RegisterActivity context, Account account){
            activityWeakReference=new WeakReference<>(context);
            this.accountToInput=account;
        }


        @Override
        protected Boolean doInBackground(Void... voids) {
            Account exists=activityWeakReference.get().appDatabase.getAccountDao().checkIfExists(accountToInput.username);
            if(exists!=null) {
                //cannot input
                accountToInput = null;
                return false;
            }else {
                activityWeakReference.get().appDatabase.getAccountDao().createAccount(accountToInput);
                return true;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean.booleanValue() == true) {
                activityWeakReference.get().setResult(accountToInput, 1);
            }
            activityWeakReference.get().setResult(accountToInput, 1);
        }
    }
}
