package com.example.emailapplication.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class MessagesFrom {

    @Embedded
    Contact contact;
    @Relation(entity = MessagesFrom.class,parentColumn = "id",entityColumn = "fromId")
    public List<Message> messages;


}
