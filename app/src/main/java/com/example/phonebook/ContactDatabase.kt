package com.example.phonebook

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.phonebook.dao.ContactDao
import com.example.phonebook.ui.Contact

@Database(
    version = 2,
    entities = [Contact::class],
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ]
)


abstract class ContactDatabase: RoomDatabase() {
    abstract val dao: ContactDao
}