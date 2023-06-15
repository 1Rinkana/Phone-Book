package com.example.phonebook.ui

sealed class Screen(val route: String) {
    object ContactScreen: Screen(route = "contact_screen")
    object ContactInfoScreen: Screen(route = "contact_info_screen")
    object EditInfoScreen: Screen(route = "edit_info_screen")
}