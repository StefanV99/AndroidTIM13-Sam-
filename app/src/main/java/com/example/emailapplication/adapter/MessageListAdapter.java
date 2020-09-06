package com.example.emailapplication.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.emailapplication.database.AppDatabase;
import com.example.emailapplication.entity.Message;

import java.util.ArrayList;

public class MessageListAdapter extends ArrayAdapter<Message> {


public MessageListAdapter(Context context, ArrayList<Message> items) {
        super(context, 0, items);


        }

    /*******************************************************************
     * FILL THE FIELDS OF THE layout
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {

    Message item = getItem(position);

        if (convertView == null) {
        convertView = LayoutInflater.from(this.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
    TextView snederOrRecv = (TextView) convertView.findViewById(android.R.id.text1);

        //snederOrRecv.setText();

        TextView subject = (TextView) convertView.findViewById(android.R.id.text2);
        subject.setText(item.getSubject());

    TextView content = (TextView) convertView.findViewById(android.R.id.message);
    subject.setText(item.getContent());

    return convertView;
        }
}
