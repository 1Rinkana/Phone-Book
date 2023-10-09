package com.example.phonebook.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.phonebook.ui.Contact

@Database(
    version = 2,
    entities = [Contact::class],
)


abstract class ContactDatabase: RoomDatabase() {
    abstract val dao: ContactDao
}
