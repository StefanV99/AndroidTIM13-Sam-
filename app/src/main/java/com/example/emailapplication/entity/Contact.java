package com.example.emailapplication.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact")
public class Contact {
    @PrimaryKey
    @NonNull
    public Long id;
    public String first;
    public String last ;
    public String display ; //username
    public String email ;
    public boolean fomrat;


    public Contact(String first, String last, String display, String email, boolean fomrat) {
        this.first = first;
        this.last = last;
        this.display = display;
        this.email = email;
        this.fomrat = fomrat;
    }

    public Long getId() {
        return id;
    }

    public String getDisplay() {
        return display;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getEmail() {
        return email;
    }

    public boolean isFomrat() {
        return fomrat;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setFomrat(boolean fomrat) {
        this.fomrat = fomrat;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", display='" + display + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
