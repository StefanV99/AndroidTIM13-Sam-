package com.example.emailapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.emailapplication.R;

public class SplashActivity extends AppCompatActivity {
Intent intentSplashLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //splash activity

/*        if(Check())
        {
            //wait for 5 secs
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    //go to another activity
                    intentSplashLogin = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intentSplashLogin);
                    finish();
                }
            }, 5000);

        }else
        {
            Toast.makeText(SplashActivity.this,getString(R.string.noInternet),Toast.LENGTH_LONG).show();
        } */
intentSplashLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intentSplashLogin);
        finish();
    }
    /*Check network connectivity*/
    boolean Check() {
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
      //  boolean connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
        return nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
    }

}
/*
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private  Button login,register;
    private EditText password,username;
    Intent intentMainReg,intentMainContacts;
    private HTTPHelper mHTTPHelper;
    private Handler mHandler;

    //    DbHelper database;


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
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.UsernameMain);
        password = findViewById(R.id.PasswordMain);
        login = findViewById(R.id.LoginBtnMain);
        register = findViewById(R.id.RegBtnMain);
        login.setEnabled(false);
        username.addTextChangedListener(TxtWatcher);
        password.addTextChangedListener(TxtWatcher);

        register.setOnClickListener(this);
        login.setOnClickListener(this);
        //database=new DbHelper(this);
        mHTTPHelper=new HTTPHelper();
        mHandler=new Handler();
        Log.d("myApp", "no network");


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.RegBtnMain):
                intentMainReg = new Intent(this, RegisterActivity.class);
                startActivity(intentMainReg);
                break;

            case (R.id.LoginBtnMain):
                // Log.w("myApp", "no network");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject jsonObject=new JSONObject();
                        try{
                            jsonObject.put(HTTPHelper.USERNAME,username.getText().toString());
                            jsonObject.put(HTTPHelper.PASSWORD,password.getText().toString());
                            //ovde preko SharedPreffs
                            final HTTPHelper.HTTPResponse res=mHTTPHelper.postJSONObjectFromUrl(HTTPHelper.URL_LOGIN,jsonObject);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if(res.responseCode==HTTPHelper.CODE_SUCCESS){
                                        Toast.makeText(MainActivity.this,getString(R.string.loginSucces),Toast.LENGTH_LONG).show();
                                        Log.w("myApp", "made it");
                                        intentMainContacts = new Intent(getApplicationContext(), ContactsActivity.class);
                                        //zasto prijavljuje problem sa this Intent konstruktor
//                                          //change the preferences with editor
                                        SharedPreferences sharedPreff=getSharedPreferences("currentUser", MODE_PRIVATE);
                                        SharedPreferences.Editor editor=sharedPreff.edit();
                                        //
                                        editor.putString(HTTPHelper.SESSION_ID,res.sessionId);
                                        editor.putString(HTTPHelper.USERNAME,username.getText().toString());
                                        editor.apply();
                                        startActivity(intentMainContacts);
                                        finish();
                                    }
                                    else if(res.responseCode==HTTPHelper.CODE_INVALID_USER_PASS) {
                                        Toast.makeText(MainActivity.this, R.string.nameNotExist, Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(MainActivity.this, R.string.errorCode + res.responseCode+res.responseMessage, Toast.LENGTH_LONG).show();
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
                }).start();
//                    Contact contact=database.search(username.getText().toString());
//                    if(contact==null) {
//                        Toast.makeText(getApplicationContext(), R.string.nameNotExist, Toast.LENGTH_LONG).show();
//                    }
//                    else {
//
//                        intentMainContacts = new Intent(this, ContactsActivity.class);
//                        //change the preferences with editor
//                        SharedPreferences sharedPreff=getSharedPreferences("currentUser", MODE_PRIVATE);
//                        SharedPreferences.Editor editor=sharedPreff.edit();
//                        editor.putLong("userId", contact.getContactId());
//                        editor.apply();
//                        startActivity(intentMainContacts);
//                        finish();
//                    }
                break;
        }
    }




}
*/