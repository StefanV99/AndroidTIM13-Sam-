package com.example.emailapplication.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.emailapplication.entity.Account;
import com.example.emailapplication.entity.AccountMessages;
import com.example.emailapplication.entity.FolderMessages;
import com.example.emailapplication.entity.Message;

import java.util.List;

@Dao
public abstract class MessagesDao {

    @Query("SELECT * FROM account WHERE id= :id")
    @Transaction
    public abstract List<AccountMessages> findAccountMessages(Long id);

  /*  @Query("SELECT * FROM folder WHERE id= :id")
    public abstract List<AccountMessages> findFolderMessages(Long id); */
    /*@Query("SELECT * FROM `message` WHERE accountId= :accountId")
    public abstract List<Message> findAccountMessages(String accountId);*/
    @Transaction
    @Query("SELECT * FROM folder WHERE id= :id ")
    public abstract List<FolderMessages> findFolderMessages(Long id);

    @Insert
    public abstract Long insertMessage(Message message);

    @Query("delete from message where id=:id")
    public abstract void delete(String id);

    @Query("delete from message")
    public abstract void deleteAll();

    @androidx.room.Update
    protected abstract void update(List<Message> messages);



}