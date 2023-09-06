package com.example.phonebook.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.phonebook.dao.ContactEvent
import com.example.phonebook.dao.ContactState
import com.example.phonebook.ui.ContactViewModel
import com.example.phonebook.ui.Screen
import com.example.phonebook.ui.screens.ContactInfoScreen
import com.example.phonebook.ui.screens.ContactScreen
import com.example.phonebook.ui.screens.EditInfoScreen

@Composable
fun Navigation(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    viewModel: ContactViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.ContactScreen.route
    ) {
        composable(Screen.ContactScreen.route) {
            ContactScreen(
                state = state,
                onEvent = onEvent,
                viewModel = viewModel,
                onNavigateToContactInfoScreen = {
                    navController.navigate("${Screen.ContactInfoScreen.route}/$it")
                }
            )
        }
        composable(
            route = "${Screen.ContactInfoScreen.route}/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                }
            )
        ) { it ->
            val param = it.arguments?.getInt("id")
            if (param != null) {
                ContactInfoScreen(
                    state = state,
                    onEvent = onEvent,
                    contactId = param,
                    navController = navController,
                    onNavigateToEditInfoScreen = {
                        navController.navigate("${Screen.EditInfoScreen.route}/$it")
                    }
                )
            }
        }
        composable(
            route = "${Screen.EditInfoScreen.route}/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                }
            )
        ) { it ->
            val param = it.arguments?.getInt("id")
            if (param != null) {
                EditInfoScreen(
                    state = state,
                    onEvent = onEvent,
                    contactId = param,
                    onNavigateToContactInfoScreen = {
                        navController.navigate("${Screen.ContactInfoScreen.route}/$it")
                    },
                )
            }
        }
    }
}
