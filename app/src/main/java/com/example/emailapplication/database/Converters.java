package com.example.emailapplication.database;

import android.util.Base64;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converters {
    /*@TypeConverter
    public static String toString(final Role role) {
        return role == null ? null : role.toString();
    }

    @TypeConverter
    public static Role toRole(String role) {
        return role == null ? null : Role.valueOf(role);
    }


    @TypeConverter
    public static String toString(EntityType entityType) {
        return entityType.toString();
    }

    @TypeConverter
    public static EntityType toEntityType(String entityType) {
        return EntityType.valueOf(entityType);
    }


    @TypeConverter
    public static EmailAddressType toEmailAddressType(String type) {
        return EmailAddressType.valueOf(type);
    }

    @TypeConverter
    public static String toString(EmailAddressType type) {
        return type.toString();
    }


    @TypeConverter
    public static EmailBodyPartType toEmailBodyPartType(String type) {
        return EmailBodyPartType.valueOf(type);
    }

    @TypeConverter
    public static String toString(EmailBodyPartType type) {
        return type.toString();
    }

*/
    @TypeConverter
    public static Base64 toBase(String file) {
        return toBase(file);
    }

    @TypeConverter
    public static String toString(Base64 file) {
        return toString(file);
    }


    @TypeConverter
    public static Date toDate(long timestamp) {
        return new Date(timestamp);
    }

    @TypeConverter
    public static long toTimestamp(Date date) {
        return date.getTime();
    }

}
