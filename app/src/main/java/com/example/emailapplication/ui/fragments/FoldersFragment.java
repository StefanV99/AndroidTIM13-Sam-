package com.example.emailapplication.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.emailapplication.R;
import com.example.emailapplication.entity.Folder;
import com.example.emailapplication.entity.Message;
import com.example.emailapplication.viewmodels.EmailListViewModel;
import com.example.emailapplication.viewmodels.FoldersViewModel;

import java.util.ArrayList;
import java.util.List;

public class FoldersFragment extends Fragment {
    public FoldersViewModel foldersViewModel;
    public Long  accountId;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        SharedPreferences prefs=this.getActivity().getSharedPreferences("currentUser", Context.MODE_PRIVATE);

        accountId=prefs.getLong("userId",0);

        foldersViewModel =
                ViewModelProviders.of(this).get(FoldersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_folders, container, false);
        View item=inflater.inflate(R.layout.fragment_emails, container, true);
        final TextView folderName = root.findViewById(R.id.text_folders);
        final TextView messageSubj = root.findViewById(R.id.message_subject);
        //final TextView messageContent = root.findViewById(R.id.message_content);
        final TextView messageDateTime = root.findViewById(R.id.message_dateTime);

        List<View> items=null;
    /*    foldersViewModel.getMessageList(accountId).observe(getViewLifecycleOwner(), new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                Message message;
                for(int i=0;i<messages.size();i++) {
                    message=messages.get(i);
                    //message.fromId
                    messageDateTime.setText(message.dateTime.toString());
                    messageSubj.setText(message.getSubject());
                    messageContent.setText(message.getContent());
                    items.add(item);
                }
                root.addChildrenForAccessibility((ArrayList<View>) items);
            }
        });
*/
        return root;


    }
}