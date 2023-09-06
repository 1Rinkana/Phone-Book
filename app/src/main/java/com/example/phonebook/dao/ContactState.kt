package com.example.phonebook.dao

import com.example.phonebook.ui.dialogs.SortType
import com.example.phonebook.ui.Contact


data class ContactState(
    val contacts: List<Contact> = emptyList(),
    val firstName: String = "",
    val lastName: String = "",
    val photo: String = "",
    val phoneNumber: String = "",
    val isAddingContact: Boolean = false,
    val isChangingSortType: Boolean = false,
    val sortType: SortType = SortType.FIRST_NAME,
)
