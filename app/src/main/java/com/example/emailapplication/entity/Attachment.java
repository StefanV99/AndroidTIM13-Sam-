package com.example.emailapplication.entity;

import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "attachment")
public class Attachment {

    @PrimaryKey
    @NonNull
    public Long id;
    public Base64 data;
    public String type ;
    public String name  ;
    public int messageId;


}
