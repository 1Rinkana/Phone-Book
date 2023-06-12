package com.example.phonebook

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactInfoScreen(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    contactId: Int
) {
    val contact = state.contacts.find { it.id == contactId } ?: state.contacts[0]
    var firstNameTxt by remember { mutableStateOf(contact.firstName) }
    var lastNameTxt by remember { mutableStateOf(contact.lastName) }
    var phoneNumber by remember { mutableStateOf(contact.phoneNumber) }
    val context = LocalContext.current
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ContactEvent.UpdateContactInfo(
                    firstName = firstNameTxt,
                    lastName = lastNameTxt,
                    phoneNumber = phoneNumber,
                    id = contactId
                ))
                Toast.makeText(context, "Contact saved", Toast.LENGTH_SHORT).show()
            }){
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
            }
        },
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(contact.photo),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                alpha = 1f,
                modifier = Modifier
                    .size(200.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .align(Alignment.CenterHorizontally)
                    .border(3.dp, Color.Black)
            )
            OutlinedTextField(
                value = firstNameTxt,
                onValueChange = { new ->
                    firstNameTxt = new
                },
                label = {
                    Text(text = "First name")
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
            )
            OutlinedTextField(
                value = lastNameTxt,
                onValueChange = { new ->
                    lastNameTxt = new
                },
                label = {
                    Text(text = "Second name")
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
            )
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { new ->
                    phoneNumber = new
                },
                label = {
                    Text(text = "Phone number")
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
            )
        }
    }
}