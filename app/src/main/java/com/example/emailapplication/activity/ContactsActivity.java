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
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.emailapplication.R;
import com.example.emailapplication.database.AppDatabase;
import com.example.emailapplication.tools.NavigationRouter;
import com.google.android.material.navigation.NavigationView;

public class ContactsActivity  extends AppCompatActivity  implements View.OnClickListener {
    AppDatabase appDatabase;
    Long currUserId=new Long(0);
    Intent intentContactsCreateContact;
    private NavigationView contactsNavigationView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contacts_action_bar, menu);
        // return true so that the menu pop up is opened

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.NewContact) {
            intentContactsCreateContact= new Intent(getApplicationContext(), CreateContactActivity.class);

            SharedPreferences sharedPreff=getSharedPreferences("currentUser", MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreff.edit();

            startActivity(intentContactsCreateContact);
            Toast.makeText(ContactsActivity.this,getString(R.string.createContact), Toast.LENGTH_LONG).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts1);

        Toolbar toolbar = findViewById(R.id.toolbar_contacts);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contacts");
        NavigationView navigationView = findViewById(R.id.contacts_nav_view);
        DrawerLayout drawer = findViewById(R.id.contacts_drawer_layout);

       contactsNavigationView = (NavigationView) findViewById(R.id.contacts_nav_view);
        contactsNavigationView.bringToFront();

        //mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        contactsNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(drawer != null) drawer.closeDrawer(Gravity.LEFT);
                return NavigationRouter.routeFromMenuItem(ContactsActivity.this, menuItem);
            }
        });

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


    }

    @Override
    public void onClick(View view) {

    }
}
