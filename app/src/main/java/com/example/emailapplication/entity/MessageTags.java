package com.example.emailapplication.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class MessageTags {

        @Embedded
        Message message;
        @Relation(
                parentColumn = "messageId",
                entityColumn = "tagId",
                associateBy = @Junction(TagsMessagesCrossRef.class)
        )
        public List<Tag> tags;
    }

