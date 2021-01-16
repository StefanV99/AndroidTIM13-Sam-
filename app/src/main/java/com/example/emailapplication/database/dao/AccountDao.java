package com.example.emailapplication.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.emailapplication.entity.Account;
@Dao
public abstract class AccountDao {
    public AccountDao(){

    }
    //LOGIN
    @Query("SELECT * FROM account WHERE password= :password AND username= :username")
    public abstract Account findAccount(String username,String password);

    @Query("SELECT * FROM account WHERE id= :id")
    public abstract Account findAccountById(String id);

    @Query("SELECT * FROM account WHERE username= :username")
    public abstract Account checkIfExists(String username);

    //REGISTER
    @Insert
    public abstract Long createAccount(Account acount);

    @Query("select exists (select 1 from account)")
    public abstract boolean hasAccounts();


}
