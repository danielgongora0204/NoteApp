package com.gig.noteapp.views.fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gig.noteapp.R
import com.gig.noteapp.components.NoteButton
import com.gig.noteapp.components.NoteTextInput
import com.gig.noteapp.data.NoteRepository
import com.gig.noteapp.models.database.Note
import com.gig.noteapp.models.ui.AppBarState
import com.gig.noteapp.navigation.NoteViews

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun NoteFragment(modifier: Modifier = Modifier, navController: NavController? = null, onComposing: (AppBarState) -> Unit = {}) {

    val notes: List<Note> = NoteRepository().loadNotes()
    var title by rememberSaveable { mutableStateOf(String()) }
    var noteBody by rememberSaveable { mutableStateOf(String()) }

    onComposing(
        AppBarState(
            title = stringResource(NoteViews.NoteFragment.title),
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications"
                    )
                }
            }
        )
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NoteTextInput(modifier = Modifier.fillMaxWidth(), text = title, label = stringResource(id = R.string.note_title_input_label)) {
            if (it.all { x ->
                x.isLetter() || x.isWhitespace()
            }
            ) {
                title = it
            }
        }
        NoteTextInput(modifier = Modifier.fillMaxWidth(), text = noteBody, label = stringResource(id = R.string.note_body_input_label)) {
            if (it.all { x ->
                x.isLetter() || x.isWhitespace()
            }
            ) {
                noteBody = it
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        NoteButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 0.dp),
            text = stringResource(id = R.string.note_save_button)
        ) {
            if (!validNote(title, noteBody)) return@NoteButton
            title = String()
            noteBody = String()
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(notes) {
                Text(text = it.title)
            }
        }
    }
}

fun onAddNote() {}
fun onRemoveNote() {}

fun validNote(title: String, noteBody: String): Boolean = title.isNotEmpty() && noteBody.isNotEmpty()

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun NoteFragmentPreview() {
    NoteFragment()
}
