package com.example.emailapplication.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.emailapplication.R;
import com.example.emailapplication.activity.RegisterActivity;
import com.example.emailapplication.entity.Account;
import com.example.emailapplication.entity.Contact;
import com.example.emailapplication.viewmodels.AccountViewModel;

import java.lang.ref.WeakReference;

public class AccountFragment extends Fragment {
    Long accountId;
    private AccountViewModel accountViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountViewModel =
                ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView username = root.findViewById(R.id.profile_username);
        final TextView password = root.findViewById(R.id.profile_password);
        final TextView pop3 = root.findViewById(R.id.profile_pop3);
        final TextView smtp = root.findViewById(R.id.profile_smtp);

        SharedPreferences prefs=this.getActivity().getSharedPreferences("currentUser", Context.MODE_PRIVATE);

        accountId=prefs.getLong("userId",0);
        accountViewModel.getAccountInfo(accountId).observe(getViewLifecycleOwner(), new Observer<Account>() {
            @Override
            public void onChanged(Account account) {
            username.setText(account.username);
                password.setText(account.password);
                pop3.setText(account.pop3Imap);
                smtp.setText(account.smtp);
            }
        });
        return root;
    }

}