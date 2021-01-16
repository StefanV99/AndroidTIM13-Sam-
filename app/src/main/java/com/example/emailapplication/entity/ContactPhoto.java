package com.example.emailapplication.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ContactPhoto {
    @Embedded
    Contact contact;
    @Relation(entity = ContactPhoto.class,parentColumn = "id",entityColumn = "contactOwnerId")
    public Photo photo;
}
