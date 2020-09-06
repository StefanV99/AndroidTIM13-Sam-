package com.example.emailapplication.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.emailapplication.R;
import com.example.emailapplication.entity.AccountMessages;
import com.example.emailapplication.entity.Message;
import com.example.emailapplication.viewmodels.EmailListViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class EmailListFragment extends Fragment {

    private EmailListViewModel emailListViewModel;
Long accountId;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View emailsFragmentView= inflater.inflate(R.layout.fragment_emails,container,false);
        ProgressBar progressBar = emailsFragmentView.findViewById(R.id.progressBarEmails);
        progressBar.setVisibility(View.VISIBLE);
        //SharedPreferences sharedPreff=getSharedPreferences("currentUser", MODE_PRIVATE);
        SharedPreferences prefs=this.getActivity().getSharedPreferences("currentUser", Context.MODE_PRIVATE);

        accountId=prefs.getLong("userId",0);

        emailListViewModel =
                ViewModelProviders.of(this).get(EmailListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_emails, container, false);
        View item=inflater.inflate(R.layout.message_item, container, true);
        final TextView messageSubj = item.findViewById(R.id.message_subject);
        final TextView messageContent = item.findViewById(R.id.message_content);
        final TextView messageDateTime = item.findViewById(R.id.message_dateTime);
        final ArrayList<View> items=new ArrayList<View>();
        //List<Message> messageList;//=emailListViewModel.getMessageList(accountId).getValue();

            emailListViewModel.getMessages(accountId).observe(getViewLifecycleOwner(), new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                Message message;
                if (messages.size() != 0) {
                    for (int i = 0; i < messages.size(); i++) {
                        message = messages.get(i);
                        //message.fromId
                        messageDateTime.setText(message.dateTime.toString());
                        messageSubj.setText(message.getSubject());
                        messageContent.setText(message.getContent());
                        items.add(item);
                    }
                    root.addChildrenForAccessibility((ArrayList<View>) items);
                }
            }
        });
        progressBar.setVisibility(View.GONE);
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}