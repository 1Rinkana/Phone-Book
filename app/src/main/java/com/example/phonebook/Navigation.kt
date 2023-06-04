package com.example.phonebook

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "contact_screen"
    ) {
        composable("contact_screen") {
            ContactScreen(
                state = state,
                onEvent = onEvent,
                onNavigateToContactInfoScreen = {
                navController.navigate("contact_info_screen/$it")
            })
        }
        composable(
            route = "contact_info_screen/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                }
            )
        ) {
            val param = it.arguments?.getInt("id")
            if (param != null) {
                ContactInfoScreen(
                    state = state,
                    onEvent = onEvent,
                    contactId = param
                )
            }
        }
    }

}