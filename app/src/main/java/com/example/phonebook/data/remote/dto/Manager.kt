package com.example.phonebook.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DataResponse(val results: List<Results>)

@Serializable
data class Results(val name: Name, val phone: String, val picture: Picture)

@Serializable
data class Name(val first: String, val last: String)

@Serializable
data class Picture(val medium: String)

