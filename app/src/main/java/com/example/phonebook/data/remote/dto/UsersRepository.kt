package com.example.phonebook.data.remote.dto

import com.example.phonebook.dao.ContactDao
import com.example.phonebook.ui.Contact

class UsersRepository(private val randomUsers: ClientApi, private val usersDao: ContactDao) {

    suspend fun getUsers(): List<Contact> {
        val localUsers = usersDao.getContacts()
        val contactList = mutableListOf<Contact>()
        if (localUsers.isNotEmpty()) { usersDao.clearTable() }
            val newUsers = randomUsers.getNewClients().results
            newUsers.forEach { user ->
                val newContact = Contact(
                    firstName = user.name.first,
                    lastName = user.name.last,
                    phoneNumber = user.phone,
                    photo = user.picture.medium
                )
                usersDao.upsertContact(newContact)
                contactList.add(newContact)
            }
        return contactList.toList()
    }
}
