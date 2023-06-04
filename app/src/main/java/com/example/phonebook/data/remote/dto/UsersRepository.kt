package com.example.phonebook.data.remote.dto

import com.example.phonebook.dao.ContactDao
import com.example.phonebook.ui.Contact

class UsersRepository(private val randomUsers: ClientApi, private val usersDao: ContactDao) {
    suspend fun getUsers(): List<Contact> {
        val localUsers = usersDao.getContacts()
        val contactList = mutableListOf<Contact>()
        if(localUsers.isEmpty()) {
            repeat(10) {
                val randomUser = randomUsers.getNewClient()
                val newContact = Contact(
                    firstName = randomUser.results.first().name.first,
                    lastName = randomUser.results.first().name.last,
                    phoneNumber = randomUser.results.first().phone,
                    photo = randomUser.results.first().picture.medium
                )
                usersDao.upsertContact(newContact)
                contactList.add(newContact)
            }
        } else {
            contactList += localUsers
        }
        return contactList.toList()
    }
}
