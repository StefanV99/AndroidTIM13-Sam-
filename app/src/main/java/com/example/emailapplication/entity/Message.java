package com.example.emailapplication.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.ZonedDateTime;
import java.util.Date;


@Entity(tableName = "message")
public class Message {

    @PrimaryKey
    @NonNull
    public Long id;
     public Long fromId;
    public Date dateTime;
    public String subject;
    public String content;
    public Long folderId;
    public Long accountId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }



    public Message( Long accountId,Long fromId, Date dateTime, String subject, String content) {
        this.fromId = fromId;
        this.dateTime = dateTime;
        this.subject = subject;
        this.content = content;
        this.accountId=accountId;
    }

    @NonNull
    public Long getId() {
        return id;
    }
    @NonNull
    public void setId(Long id) {
        this.id = id;
    }


    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}