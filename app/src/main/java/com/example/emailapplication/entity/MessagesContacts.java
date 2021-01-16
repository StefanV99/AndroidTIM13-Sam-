package com.example.emailapplication.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class MessagesContacts {
    @Embedded Message message;
    @Relation(
            parentColumn = "messageId",
            entityColumn = "contactId",
            associateBy = @Junction(MessagesContactsCrossRef.class)
    )
    public List<Contact> contacts;
}
