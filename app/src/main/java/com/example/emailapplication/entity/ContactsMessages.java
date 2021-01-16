package com.example.emailapplication.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class ContactsMessages {
    @Embedded public  Contact contact;
    @Relation(
            parentColumn = "contactId",
            entityColumn = "messageId",
            associateBy = @Junction(MessagesContactsCrossRef.class)
    )
    List<Message> messages;
}
