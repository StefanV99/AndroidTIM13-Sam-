package com.example.emailapplication.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "account")
public class Account {
        public int DEFAULT=0;
        @NonNull
        @PrimaryKey
        public Long id;
        public String username;
        public String password ;
        public int smtp; //smtp protocol port
        public int pop3Imap; //pop3Imap protocol port

        public Account( String username, String password, int smtp, int pop3Imap) {
                this.username = username;
                this.password = password;
                this.smtp = smtp;
                this.pop3Imap = pop3Imap;
        }
        @Ignore
        public Account( String username, String password) {
                this.username = username;
                this.password = password;
                this.smtp = DEFAULT;
                this.pop3Imap = DEFAULT;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getPassword() {
                return password;
        }

        public String getUsername() {
                return username;
        }

        public Long getId() {
                return id;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public int isSmtp() {
                return smtp;
        }

        public void setSmtp(int smtp) {
                this.smtp = smtp;
        }

        public int isPop3Imap() {
                return pop3Imap;
        }

        public void setPop3Imap(int pop3Imap) {
                this.pop3Imap = pop3Imap;
        }
        public Boolean isValid()
        {
                return  this.username!=null && this.password!=null;
        }
}
