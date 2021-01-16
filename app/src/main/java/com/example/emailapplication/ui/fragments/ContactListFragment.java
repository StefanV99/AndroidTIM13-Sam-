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
import com.example.emailapplication.entity.Contact;
import com.example.emailapplication.entity.Message;
import com.example.emailapplication.viewmodels.ContactListViewModel;
import com.example.emailapplication.viewmodels.EmailListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContactListFragment extends Fragment {

    private long accountId;
    private ContactListViewModel contactListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View contactListFragmentView= inflater.inflate(R.layout.fragment_contact_list,container,false);


        //SharedPreferences sharedPreff=getSharedPreferences("currentUser", MODE_PRIVATE);
        SharedPreferences prefs=this.getActivity().getSharedPreferences("currentUser", Context.MODE_PRIVATE);

        accountId=prefs.getLong("userId",0);

        contactListViewModel=
                ViewModelProviders.of(this).get(ContactListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contact_list, container, false);
        View item=inflater.inflate(R.layout.contact_item, container, true);
        final TextView  firstLetter= item.findViewById(R.id.FirstLetter);

        //final TextView messageContent = item.findViewById(R.id.message_content);
        final TextView firstName = item.findViewById(R.id.Name);
        final ArrayList<View> items=new ArrayList<>();
        //List<Message> messageList;//=emailListViewModel.getMessageList(accountId).getValue();

        contactListViewModel.getContacts(accountId).observe(getViewLifecycleOwner(), (Observer<List<Contact>>) contacts -> {
            Contact contact;
            if (contacts.size() != 0) {
                for (int i = 0; i < contacts.size(); i++) {
                    contact = contacts.get(i);
                    //contact.fromId
                    firstName.setText(contact.first);
                   firstLetter.setText(String.valueOf(contact.first.charAt(0)));

                    // messageContent.setText(message.getContent());
                    items.add(item);
                }
                root.addChildrenForAccessibility((ArrayList<View>) items);
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contactListViewModel= ViewModelProviders.of(this).get(ContactListViewModel.class);
    }
}
