package com.example.phonebook

import com.example.phonebook.ui.Contact

sealed interface ContactEvent {
    object SaveContact: ContactEvent
    data class SetFirstName(val firstName: String): ContactEvent
    data class SetLastName(val lastName: String): ContactEvent
    data class SetPhoto(val photo: String): ContactEvent
    data class SetPhoneNumber(val phoneNumber: String): ContactEvent
    object ShowDialog: ContactEvent
    object HideDialog: ContactEvent
    data class SortContacts(val sortType: SortType): ContactEvent
    data class DeleteContact(val contact: Contact): ContactEvent
    object GetContacts: ContactEvent
    object ShowSortTypes: ContactEvent
    object HideSortTypes: ContactEvent
    class ShowFullInfo(val contact: Contact): ContactEvent
    object ReturnToMainPage: ContactEvent
}