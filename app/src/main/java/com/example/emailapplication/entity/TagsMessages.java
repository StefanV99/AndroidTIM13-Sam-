package com.example.emailapplication.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class TagsMessages {
    @Embedded
    Tag tag;
    @Relation(
            parentColumn = "tagId",
            entityColumn = "messageId",
            associateBy = @Junction(TagsMessagesCrossRef.class)
    )
    public List<Message> messages;
}
