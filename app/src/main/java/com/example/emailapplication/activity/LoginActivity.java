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

import com.example.emailapplication.R;
import com.example.emailapplication.database.AppDatabase;
import com.example.emailapplication.database.dao.AccountDao;
import com.example.emailapplication.entity.Account;



import java.lang.ref.WeakReference;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button login,register;
    private EditText password,username;
    Intent intentLoginRegister,intentLoginMessages;
    //private HTTPHelper mHTTPHelper;
    private Handler mHandler;
        AppDatabase appDatabase;
        //DbHelper database;



    void Check() {
        if ((password.length() >6) && (username.getText().toString().isEmpty()!=true)) {
            login.setEnabled(true);

        } else {
            login.setEnabled(false);
        }
    }

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        appDatabase=AppDatabase.getInstance(LoginActivity.this);
        username = findViewById(R.id.UsernameLogin);
        password = findViewById(R.id.PasswordLogin);
        login = findViewById(R.id.LoginBtnLogin);
        register = findViewById(R.id.RegBtnLogin);
        login.setEnabled(false);
        username.addTextChangedListener(TxtWatcher);
        password.addTextChangedListener(TxtWatcher);

        register.setOnClickListener(this);
        login.setOnClickListener(this);
        //database=new DbHelper(this);
        //mHTTPHelper=new HTTPHelper();
        mHandler=new Handler();
        Log.d("myApp", "no network");


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.RegBtnLogin):
                intentLoginRegister = new Intent(this, RegisterActivity.class);
                startActivity(intentLoginRegister);
                finish();
                break;

            case (R.id.LoginBtnLogin):
                // Log.w("myApp", "no network");
                        /*JSONObject jsonObject=new JSONObject();
                        try{
                            jsonObject.put(HTTPHelper.USERNAME,username.getText().toString());
                            jsonObject.put(HTTPHelper.PASSWORD,password.getText().toString());
                            //ovde preko SharedPreffs
                            final HTTPHelper.HTTPResponse res=mHTTPHelper.postJSONObjectFromUrl(HTTPHelper.URL_LOGIN,jsonObject);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if(res.responseCode==HTTPHelper.CODE_SUCCESS){
                                        Toast.makeText(LoginActivity.this,getString(R.string.loginSucces),Toast.LENGTH_LONG).show();
                                        Log.w("myApp", "made it");
                                        intentLoginContacts = new Intent(getApplicationContext(), ContactsActivity.class);
                                        //zasto prijavljuje problem sa this Intent konstruktor
//                                          //change the preferences with editor
                                        SharedPreferences sharedPreff=getSharedPreferences("currentUser", MODE_PRIVATE);
                                        SharedPreferences.Editor editor=sharedPreff.edit();
                                        //
                                        editor.putString(HTTPHelper.SESSION_ID,res.sessionId);
                                        editor.putString(HTTPHelper.USERNAME,username.getText().toString());
                                        editor.apply();
                                        startActivity(intentLoginContacts);
                                        finish();
                                    }
                                    else if(res.responseCode==HTTPHelper.CODE_INVALID_USER_PASS) {
                                        Toast.makeText(LoginActivity.this, R.string.nameNotExist, Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(LoginActivity.this, R.string.errorCode + res.responseCode+res.responseMessage, Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        catch(JSONException e) {
                            e.printStackTrace();
                        }
                        catch(IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start(); */
                        Account loginAcc=new Account(username.getText().toString(),password.getText().toString());
                        new LoginTask(LoginActivity.this,loginAcc).execute();


        }
    }
    private void setResult(Account acc,int flag){
        if(acc==null)
        {
            Toast.makeText(getApplicationContext(), R.string.nameNotExist, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(LoginActivity.this,getString(R.string.loginSucces),Toast.LENGTH_LONG).show();

            intentLoginMessages= new Intent(getApplicationContext(), MessagesActivity.class);

            //change the preferences with editor

            SharedPreferences sharedPreff=getSharedPreferences("currentUser", MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreff.edit();

            editor.putLong("userId",acc.getId().longValue());
            editor.putString("username",acc.getUsername());
            editor.apply();
            startActivity(intentLoginMessages);
           // setResult(flag,new Intent().putExtra("account",acc));
            finish();
        }
    }
    private static class LoginTask extends AsyncTask<Void,Void,Boolean>{
        private WeakReference<LoginActivity> activityWeakReference;
        private Account accountToInput;
        private Account recievedAccount;

        LoginTask(LoginActivity context, Account account){
        activityWeakReference=new WeakReference<>(context);
        this.accountToInput=account;
    }


        @Override
        protected Boolean doInBackground(Void... voids) {
        recievedAccount=activityWeakReference.get().appDatabase.getAccountDao().findAccount(accountToInput.username,accountToInput.password);
                return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean.booleanValue()==true)
                activityWeakReference.get().setResult(recievedAccount,1);
        }
    }
}

