package com.example.phonebook.ui

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity
data class Contact (
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val photo: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)