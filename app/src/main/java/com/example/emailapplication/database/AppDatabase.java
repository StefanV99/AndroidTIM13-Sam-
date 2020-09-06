/*
 * Copyright 2019 Daniel Gultsch
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.emailapplication.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.emailapplication.activity.MessagesActivity;
import com.example.emailapplication.database.dao.AccountDao;
import com.example.emailapplication.database.dao.ContactDao;
import com.example.emailapplication.database.dao.FolderDao;
import com.example.emailapplication.database.dao.MessagesDao;
import com.example.emailapplication.entity.Account;
import com.example.emailapplication.entity.AccountMessages;
import com.example.emailapplication.entity.Attachment;
import com.example.emailapplication.entity.Contact;
import com.example.emailapplication.entity.ContactPhoto;
import com.example.emailapplication.entity.Folder;
import com.example.emailapplication.entity.FolderMessages;
import com.example.emailapplication.entity.FolderToFolder;
import com.example.emailapplication.entity.Message;
import com.example.emailapplication.entity.MessageAttachments;
import com.example.emailapplication.entity.MessageTags;
import com.example.emailapplication.entity.MessagesContacts;
import com.example.emailapplication.entity.MessagesContactsCrossRef;
import com.example.emailapplication.entity.MessagesFrom;
import com.example.emailapplication.entity.Photo;
import com.example.emailapplication.entity.Rule;
import com.example.emailapplication.entity.Tag;
import com.example.emailapplication.entity.TagsMessages;
import com.example.emailapplication.entity.TagsMessagesCrossRef;


@Database(
        entities = {
                Account.class,
                //AccountMessages.class,
                Message.class,
                //MessagesFrom.class,
                Contact.class,
                Photo.class,
                //ContactPhoto.class,
                //MessagesContacts.class,
                //MessagesContactsCrossRef.class,
                //MessageTags.class,
                Tag.class,
                //TagsMessages.class,
                //TagsMessagesCrossRef.class,
                Folder.class,
                //FolderToFolder.class,
                //FolderMessages.class,
                Attachment.class,
                //MessageAttachments.class,
                Rule.class

        },
        version = 2,
        exportSchema = false
)

@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {


    private static volatile AppDatabase INSTANCE = null;

   // public abstract SearchSuggestionDao searchSuggestionDao();

    public abstract AccountDao getAccountDao();
    public abstract MessagesDao getMessagesDao();
    public abstract FolderDao getFolderDao();
    public abstract ContactDao getContactDao();

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        synchronized (AppDatabase.class) {
            if (INSTANCE != null) {
                return INSTANCE;
            }
            INSTANCE=Room.databaseBuilder(context, AppDatabase.class, "app").fallbackToDestructiveMigration().build();
            //INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "app").allowMainThreadQueries().build();
            return INSTANCE;
        }
    }

}
