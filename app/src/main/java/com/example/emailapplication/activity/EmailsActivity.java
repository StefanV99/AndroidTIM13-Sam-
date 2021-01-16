package com.example.emailapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
//import androidx;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.emailapplication.R;
import com.example.emailapplication.database.AppDatabase;
import com.example.emailapplication.tools.NavigationRouter;
import com.example.emailapplication.viewmodels.EmailListViewModel;
import com.google.android.material.navigation.NavigationView;


public class EmailsActivity extends AppCompatActivity implements View.OnClickListener {
    EmailListViewModel viewModel;
    AppDatabase appDatabase;
    Long currUserId=new Long(0);
    CharSequence mTitle;
    private AppBarConfiguration mAppBarConfiguration;
    Intent intentMessagesCreateMessage;
    private NavigationView accountsNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_emails);
        Toolbar toolbar = findViewById(R.id.toolbar_emails);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Inbox");
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutAccounts);
        accountsNavigationView = (NavigationView) findViewById(R.id.emails_nav_view);
        accountsNavigationView.bringToFront();

        //mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        accountsNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(drawer != null) drawer.closeDrawer(Gravity.LEFT);
                return NavigationRouter.routeFromMenuItem(EmailsActivity.this, menuItem);
            }
        });

        //NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            default:
                break;

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.emails_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.newMessage) {
            intentMessagesCreateMessage= new Intent(getApplicationContext(), CreateMessageActivity.class);

            SharedPreferences sharedPreff=getSharedPreferences("currentUser", MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreff.edit();


            startActivity(intentMessagesCreateMessage);

            Toast.makeText(EmailsActivity.this,getString(R.string.createMessage), Toast.LENGTH_LONG).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
