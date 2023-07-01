package com.example.phonebook

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.phonebook.ui.Screen
import com.example.phonebook.ui.theme.primary
import kotlinx.coroutines.DelicateCoroutinesApi

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun ContactInfoScreen(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    contactId: Int,
    navController: NavController,
    onNavigateToEditInfoScreen: (Int) -> Unit
) {
    val contact = state.contacts.find { it.id == contactId } ?: state.contacts[0]


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Contact info")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = primary
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(route = Screen.ContactScreen.route)
                            onEvent(ContactEvent.DeleteContact(contact))
                        }
                    ) {
                        Icon(Icons.Outlined.Delete, "Delete contact")
                    }
                    IconButton(
                        onClick = { onNavigateToEditInfoScreen(contact.id) }
                    ) {
                        Icon(Icons.Rounded.Edit, "Edit contact")
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigate(route = Screen.ContactScreen.route) }
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Image(
                painter = rememberAsyncImagePainter(contact.photo),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "${contact.firstName} ${contact.lastName}",
                fontStyle = FontStyle.Normal,
                fontSize = 30.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(size = 70.dp)
                    .padding(8.dp)
                ,
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = "Phone number",
                        fontSize = 15.sp
                    )
                    Text(
                        text = contact.phoneNumber,
                        fontStyle = FontStyle.Normal,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}
