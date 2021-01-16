package com.example.emailapplication.entity;

import androidx.room.Entity;
//associative entity- no parent and child classes in the database
@Entity(primaryKeys = {"contactId","messageId"})
public class MessagesContactsCrossRef {
    public long contactId;
    public long messageId;
}
