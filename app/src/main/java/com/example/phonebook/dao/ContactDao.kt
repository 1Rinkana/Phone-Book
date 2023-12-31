package com.example.phonebook.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.phonebook.ui.Contact
import kotlinx.coroutines.flow.Flow


@Dao
interface ContactDao {

    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("DELETE FROM contact")
    suspend fun clearTable()

    @Query("SELECT * FROM contact")
    fun getContacts() : List<Contact>

    @Query("SELECT * FROM contact ORDER BY firstName ASC")
    fun getContactsOrderedByFirstName(): Flow<List<Contact>>

    @Query("SELECT * FROM contact ORDER BY lastName ASC")
    fun getContactsOrderedByLastName(): Flow<List<Contact>>

    @Query("SELECT * FROM contact ORDER BY phoneNumber ASC")
    fun getContactsOrderedByPhoneNumber(): Flow<List<Contact>>

    @Query("UPDATE contact SET firstName=:firstName,lastName=:lastName,phoneNumber=:phoneNumber WHERE id=:id")
    fun updateContactInfo(firstName: String, lastName: String, phoneNumber: String, id: Int)
}
