package com.example.emailapplication.entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"tagId","messageId"})
public class TagsMessagesCrossRef {
    public long tagId;
    public long messageId;

}
