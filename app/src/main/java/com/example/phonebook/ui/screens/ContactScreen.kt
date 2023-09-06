package com.example.phonebook.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.phonebook.dao.ContactState
import com.example.phonebook.ui.ContactViewModel
import com.example.phonebook.dao.ContactEvent
import com.example.phonebook.ui.dialogs.AddContactDialog
import com.example.phonebook.ui.dialogs.ChangeSortTypeDialog
import com.example.phonebook.ui.theme.primary
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    viewModel: ContactViewModel,
    onNavigateToContactInfoScreen: (Int) -> Unit,
) {
    val systemUiController = rememberSystemUiController()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    systemUiController.setSystemBarsColor(
        color = primary,
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Contacts")
                },
                navigationIcon = {
                    IconButton(onClick = { onEvent(ContactEvent.ShowSortTypes) }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Change Sort Type",
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = primary,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 4.dp),
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEvent(ContactEvent.ShowDialog) },
                containerColor = Color.LightGray,
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add contact")
            }
        }
    ) { padding ->
        if (state.isAddingContact) {
            AddContactDialog(state = state, onEvent = onEvent)
        }

        if (state.isChangingSortType) {
            ChangeSortTypeDialog(state = state, onEvent = onEvent)
        }

        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
            onRefresh = { onEvent(ContactEvent.GetContacts) }
        ) {
            LazyColumn(
                contentPadding = padding,
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.contacts) {contact ->
                    ElevatedCard(
                        onClick = { onNavigateToContactInfoScreen(contact.id) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(size = 70.dp)
                        ,
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(all = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(contact.photo),
                                contentDescription = "avatar",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                            )

                            Spacer(modifier = Modifier.width(width = 8.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "${contact.firstName} ${contact.lastName}",
                                    fontSize = 20.sp,
                                )

                                Text(text = contact.phoneNumber, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
