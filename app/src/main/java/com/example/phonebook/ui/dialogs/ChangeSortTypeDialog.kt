package com.example.phonebook.ui.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.phonebook.dao.ContactEvent
import com.example.phonebook.dao.ContactState

@Composable
fun ChangeSortTypeDialog(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onEvent(ContactEvent.HideSortTypes) },
        title = { Text(
            text = "Select Sort Type",
            style = MaterialTheme.typography.titleLarge,
        ) },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SortType.values().forEach { sortType ->
                    Row(
                        modifier = Modifier.clickable { onEvent(ContactEvent.SortContacts(sortType)) },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = state.sortType == sortType,
                            onClick = { onEvent(ContactEvent.SortContacts(sortType)) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color.Black,
                            ),
                        )

                        Text(
                            text = sortType.name.lowercase().replace("_", " "),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
        },

        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd,
            ) {
                Button(
                    onClick = { onEvent(ContactEvent.HideSortTypes) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                ) {
                    Text(
                        text = "Confirm",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        },

        containerColor = Color.LightGray,
    )
}
