package com.example.phonebook

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactInfoScreen(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    contactId: Int
) {
    val contact = state.contacts[contactId]
    Scaffold(
        modifier = Modifier.padding(10.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(contact.photo),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            alpha = 1f,
            modifier = Modifier
                .size(70.dp)
                .padding(5.dp)
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "${contact.firstName} ${contact.lastName}",
                fontSize = 20.sp
            )
            Text(text = contact.phoneNumber, fontSize = 12.sp)
        }
    }
}