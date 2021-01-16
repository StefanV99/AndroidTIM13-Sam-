package com.example.emailapplication.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class FolderToFolder {
    @Embedded
    Folder folderOut;
    @Relation(entity = FolderToFolder.class,parentColumn = "id",entityColumn = "folderId",projection = "name")
    public List<String> folderInName ;
}
