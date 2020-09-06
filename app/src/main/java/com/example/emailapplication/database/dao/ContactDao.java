package com.example.emailapplication.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.emailapplication.entity.Account;
import com.example.emailapplication.entity.Contact;

import java.util.List;

@Dao
public abstract class ContactDao {
    @Query("SELECT * FROM `contact`")
    public abstract List<Contact> allContacts();
    @Query("DELETE  FROM `contact`")
    public abstract void deleteAllContacts();
    @Query("DELETE  FROM `contact` WHERE id= :contactId")
    public abstract void deleteAllContacts(String contactId);
    @Query("SELECT *  FROM `contact` WHERE id= :contactId")
    public abstract List<Contact> selectContact(String contactId);

    @Query("UPDATE `contact` SET first= :name WHERE id= :id")
    public abstract void EditContactName(String name,String id);

    @Query("UPDATE `contact` SET last= :lastName WHERE id= :id")
    public abstract void EditContactLastName(String lastName,String id);
    @Query("SELECT * FROM `contact` WHERE email= :username ")
    public abstract Contact getContactByUsername(String username);
    @Insert
    public abstract Long CreateContact(Contact contact);

}
