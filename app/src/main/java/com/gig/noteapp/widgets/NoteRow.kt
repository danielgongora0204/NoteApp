package com.gig.noteapp.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gig.noteapp.models.database.Note
import com.gig.noteapp.utilities.extensions.default

@ExperimentalMaterial3Api
@Preview
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note? = null,
    onNoteRowClick: (Note) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        onClick = { note?.let { onNoteRowClick.invoke(it) } }
    ) {
        Column(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            Text(
                text = note?.title.default("Hello There"),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
