package com.example.emailapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
//import androidx;
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
import com.example.emailapplication.ui.fragments.AccountFragment;
import com.example.emailapplication.ui.fragments.EmailListFragment;
import com.example.emailapplication.ui.fragments.FoldersFragment;
import com.example.emailapplication.ui.fragments.SettingsFragment;
import com.example.emailapplication.viewmodels.EmailListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.zip.Inflater;

public class MessagesActivity extends AppCompatActivity implements View.OnClickListener {
    EmailListViewModel viewModel;
    AppDatabase appDatabase;
    Long currUserId=new Long(0);
    private AppBarConfiguration mAppBarConfiguration;
    Intent intentMessagesCreateMessage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_emails);
        Toolbar toolbar = findViewById(R.id.toolbarGeneral);
        setSupportActionBar(toolbar);
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_account, R.id.nav_settings)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentMessagesCreateMessage = new Intent(getApplicationContext(), CreateMessageActivity.class);

                //change the preferences with editor

                // SharedPreferences sharedPreff = getSharedPreferences("currentUser", MODE_PRIVATE);
                //SharedPreferences.Editor editor = sharedPreff.edit();

                // editor.putLong("userId", acc.getId().longValue());
                // editor.apply();
                startActivity(intentMessagesCreateMessage);
                // setResult(flag,new Intent().putExtra("account",acc));
                finish();
            }});

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        /*    case (R.id.fab):
                //Toast.makeText(LoginActivity.this,getString(R.string.loginSucces),Toast.LENGTH_LONG).show();

                intentMessagesCreateMessage = new Intent(getApplicationContext(), CreateMessageActivity.class);

                //change the preferences with editor

               // SharedPreferences sharedPreff = getSharedPreferences("currentUser", MODE_PRIVATE);
                //SharedPreferences.Editor editor = sharedPreff.edit();

               // editor.putLong("userId", acc.getId().longValue());
               // editor.apply();
                startActivity(intentMessagesCreateMessage);
                // setResult(flag,new Intent().putExtra("account",acc));
                finish();
                break; */
            default:
                break;


        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.emails_new, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
