package com.example.emailapplication.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class FolderMessages {
    @Embedded
    public Folder folder;
    @Relation(entity = Message.class,parentColumn = "id",entityColumn = "folderId")
    public List<Message> messages;

}
