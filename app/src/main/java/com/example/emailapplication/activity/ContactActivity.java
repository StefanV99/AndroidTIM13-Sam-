package com.example.emailapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.emailapplication.R;
import com.example.emailapplication.tools.NavigationRouter;
import com.google.android.material.navigation.NavigationView;

public class ContactActivity  extends AppCompatActivity implements View.OnClickListener {
    private Intent intentContactContacts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contact_action_bar, menu);
        // return true so that the menu pop up is opened

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.contact_back) {
            intentContactContacts= new Intent(getApplicationContext(), CreateContactActivity.class);

            SharedPreferences sharedPreff=getSharedPreferences("currentUser", MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreff.edit();

            startActivity(intentContactContacts);
            Toast.makeText(ContactActivity.this,getString(R.string.createContact), Toast.LENGTH_LONG).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {

    }
}
