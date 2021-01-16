package com.example.emailapplication.entity;
//account is the primary key(one) messages is the foreign(many)

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public  class AccountMessages {
@Embedded
 public Account account;
 @Relation(entity = Message.class,parentColumn = "id",entityColumn = "accountId")
    public  List<Message> messages;
}

