package com.example.emailapplication.activity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.emailapplication.R;
import com.example.emailapplication.tools.NavigationRouter;
import com.example.emailapplication.ui.fragments.AccountFragment;
import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity  {
    private NavigationView accountsNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutAccounts);
        accountsNavigationView = (NavigationView) findViewById(R.id.profile_nav_view);
        accountsNavigationView.bringToFront();
        //loadFragment(new AccountFragment());

        //mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        accountsNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(drawer != null) drawer.closeDrawer(Gravity.LEFT);
                return NavigationRouter.routeFromMenuItem(ProfileActivity.this, menuItem);
            }
        });

    }
/*
    private void loadFragment(Fragment accountFragment) {
        // create a FragmentManager
            FragmentManager fm = getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
            fragmentTransaction.replace(R.id.frameLayout_profile, accountFragment);
            fragmentTransaction.commit(); // save the changes
        }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       // return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.profile_action_bar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save_changes_profile) {
            //inentProfile= new Intent(getApplicationContext(), CreateMessageActivity.class);

            SharedPreferences sharedPreff=getSharedPreferences("currentUser", MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreff.edit();
            editor.remove("currentUser");



         //   startActivity(intentMessagesCreateMessage);

            Toast.makeText(ProfileActivity.this,getString(R.string.profile_info_changed), Toast.LENGTH_LONG).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
