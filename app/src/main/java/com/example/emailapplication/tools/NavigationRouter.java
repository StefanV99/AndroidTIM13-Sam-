package com.example.emailapplication.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.emailapplication.R;
import com.example.emailapplication.activity.ContactsActivity;
import com.example.emailapplication.activity.EmailsActivity;
import com.example.emailapplication.activity.LoginActivity;
import com.example.emailapplication.activity.ProfileActivity;

public interface NavigationRouter {
    public static boolean routeFromMenuItem(Activity context, @NonNull MenuItem item){
        switch (item.getItemId()){

            case R.id.nav_home: {
                if(context.getClass() != EmailsActivity.class){
                    Intent intent = new Intent(context, EmailsActivity.class);
                    context.startActivity(intent);
                    return true;
                }
                return true;
            }

            case R.id.nav_contacts: {
                if(context.getClass() != ContactsActivity.class){
                    Intent intent = new Intent(context, ContactsActivity.class);
                    context.startActivity(intent);
                    return true;
                }
                return true;

            }

            case R.id.nav_folders: {
               /* if(context.getClass() != FoldersActivity.class){
                    Intent intent = new Intent(context, FoldersActivity.class);
                    context.startActivity(intent);
                    return true;
                } */
                return true;
            }

            case R.id.nav_acmcount: {
               if(context.getClass() != ProfileActivity.class){
                    Intent intent = new Intent(context, ProfileActivity.class);
                    context.startActivity(intent);
                    return true;
                }
                return true;
            }

            case R.id.nav_tags: {
                /*if(context.getClass() != TagsActivity.class){
                    Intent intent = new Intent(context, TagsActivity.class);
                    context.startActivity(intent);
                    return true;
                } */
                return true;
            }

            case R.id.nav_rules: {
              /*  if(context.getClass() != RulesActivity.class){
                    Intent intent = new Intent(context, RulesActivity.class);
                    context.startActivity(intent);
                    return true;
                }
                return true; */
            }

            case R.id.nav_settings: {
              /*  if(context.getClass() != SettingsActivity.class){
                    Intent intent = new Intent(context, SettingsActivity.class);
                    context.startActivity(intent);
                    return true;
                } */
                return true;
            }
            case R.id.action_sign_out: {

                Intent intent = new Intent (context, LoginActivity.class);
                //context.getSharedPreferences("userId", Context.MODE_PRIVATE);

                context.startActivity(intent);

                return true;

            }


            default: return false;
        }
    }


}

