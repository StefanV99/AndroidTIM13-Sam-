package com.example.emailapplication.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rule")
public class Rule {
    @NonNull
    @PrimaryKey
    public  Long id;
    public   int operation;
    public   int condition;

    public  Long getId() {
        return id;
    }

    public  void setId(Long id) {
        this.id = id;
    }

    public  int getOperation() {
        return operation;
    }

    public  void setOperation(int operation) {
        this.operation = operation;
    }

    public  int getCondition() {
        return condition;
    }

    public  void setCondition(int condition) {
        this.condition = condition;
    }

    public Rule() {
    }
}
