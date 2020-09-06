package com.example.emailapplication.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class MessageAttachments {
    @Embedded
    Message message;
    @Relation(entity = MessageAttachments.class,parentColumn = "id",entityColumn = "messageId",projection = "attachment")
    public List<Attachment> attachments;


}
