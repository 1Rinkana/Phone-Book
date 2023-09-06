package com.example.phonebook.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phonebook.dao.ContactState
import com.example.phonebook.dao.ContactEvent
import com.example.phonebook.ui.theme.primary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditInfoScreen(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    contactId: Int,
    onNavigateToContactInfoScreen: (Int) -> Unit,
) {
    val contact = state.contacts.find { it.id == contactId } ?: state.contacts[0]
    var firstNameTxt by remember { mutableStateOf(contact.firstName) }
    var lastNameTxt by remember { mutableStateOf(contact.lastName) }
    var phoneNumber by remember { mutableStateOf(contact.phoneNumber) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Edit info")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = primary
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { onNavigateToContactInfoScreen(contact.id) }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 4.dp)
            )
        },
    ) { padding->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            OutlinedTextField(
                value = firstNameTxt,
                onValueChange = { new -> firstNameTxt = new },
                label = { Text(text = "First name") },
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Divider(
                color = Color.Black,
                thickness = 1.dp,
                modifier = Modifier
                    .width(40.dp)
                    .align(Alignment.CenterHorizontally),
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                OutlinedTextField(
                    value = lastNameTxt,
                    onValueChange = { new -> lastNameTxt = new },
                    label = { Text(text = "Second name") },
                    modifier = Modifier
                        .width(150.dp)
                        .padding(8.dp),
                )
                OutlinedTextField(
                    value = lastNameTxt,
                    onValueChange = { new -> lastNameTxt = new },
                    label = { Text(text = "Second name") },
                    modifier = Modifier
                        .width(150.dp)
                        .padding(8.dp),
                )
            }

            Row(
                modifier = Modifier
                    .width(316.dp)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = true,
                    onCheckedChange = null,
                    modifier = Modifier.padding(4.dp),
                    enabled = true,
                )
                Text(
                    text = "Enable big rest time (extended rest every X rounds).",
                    modifier = Modifier,
                    color = Color.Black,
                )
                Box(modifier = Modifier.width(24.dp))
            }

            OutlinedTextField(
                value = lastNameTxt,
                onValueChange = { new -> lastNameTxt = new },
                label = { Text(text = "Second name") },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
            )

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { new -> phoneNumber = new },
                label = { Text(text = "Phone number") },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
            )
            Spacer(modifier = Modifier.height(60.dp))
            Button(
                onClick = {
                    onEvent(
                        ContactEvent.UpdateContactInfo(
                        firstName = firstNameTxt,
                        lastName = lastNameTxt,
                        phoneNumber = phoneNumber,
                        id = contactId,
                    ))
                    onNavigateToContactInfoScreen(contact.id)
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(60.dp)
                    .width(160.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
            ) {
                Text(
                    text = "Confirm",
                    fontSize = 20.sp,
                )
            }
        }
    }
}
