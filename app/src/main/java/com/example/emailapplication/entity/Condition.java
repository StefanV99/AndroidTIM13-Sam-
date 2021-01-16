package com.example.emailapplication.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;


public class Condition {
    public static final int to=0;
    public static final int from=1;
    public static final int cc=2;
    public static final int subject=3;



    public String toString(int number) {
        switch(number){
            case 0:
                return "TO";

            case 1:
                return "FROM";

            case 2:
                return "CC";

            case 3:
                return "SUBJECT";

            default:
                return "TO";

        }

    }
}
