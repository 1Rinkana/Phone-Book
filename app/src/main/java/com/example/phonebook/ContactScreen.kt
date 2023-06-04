package com.example.phonebook

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.phonebook.dialogs.AddContactDialog
import com.example.phonebook.dialogs.ChangeSortTypeDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    onNavigateToContactInfoScreen: (Int) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ContactEvent.ShowDialog)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add contact")
            }
        },
        modifier = Modifier.padding(10.dp)
    ) { padding ->
        if (state.isAddingContact) {
            AddContactDialog(state = state, onEvent = onEvent)
        }
        if (state.isChangingSortType) {
            ChangeSortTypeDialog(state = state, onEvent = onEvent)
        }
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Change sort type:")
                    IconButton(onClick = {
                        onEvent(ContactEvent.ShowSortTypes)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Change Sort Type"
                        )
                    }
                }
            }
            items(state.contacts) {contact ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        border = BorderStroke(1.dp, Color.Black),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                        onClick = { onNavigateToContactInfoScreen(contact.id) },
                        modifier = Modifier
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(contact.photo),
                            contentDescription = "avatar",
                            contentScale = ContentScale.Crop,
                            alpha = 1f,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(5.dp)
                                .clip(CircleShape)
                        )
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "${contact.firstName} ${contact.lastName}",
                                fontSize = 20.sp
                            )
                            Text(text = contact.phoneNumber, fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}