package com.gig.noteapp.views.fragments

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gig.noteapp.R
import com.gig.noteapp.components.NoteButton
import com.gig.noteapp.components.NoteTextInput
import com.gig.noteapp.models.database.Note
import com.gig.noteapp.models.ui.AppBarState
import com.gig.noteapp.models.ui.appBarState
import com.gig.noteapp.navigation.NoteViews
import com.gig.noteapp.widgets.NoteConfirmationBottomSheet
import com.gig.noteapp.widgets.NoteRow
import java.time.LocalDateTime

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun NoteFragment(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    notes: List<Note> = emptyList(),
    onAddNote: (Note) -> Unit = {},
    onRemoveNote: (Note) -> Unit = {},
    onComposing: (AppBarState) -> Unit = {}
) {
    val context = LocalContext.current
    var title by rememberSaveable { mutableStateOf(String()) }
    var noteBody by rememberSaveable { mutableStateOf(String()) }
    var showSheet by rememberSaveable { mutableStateOf(false) }
    onComposing(
        appBarState(
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
    if (showSheet) { 
        NoteConfirmationBottomSheet(
            modifier = Modifier,
            title = stringResource(id = R.string.note_save_confirm_title),
            message = stringResource(id = R.string.note_save_confirm_message),
            cancelText = stringResource(id = R.string.cancel_button),
            confirmText = stringResource(id = R.string.ok_button),
            onCancel = { showSheet = false  }
        ) {
            onAddNote(
                Note(
                    title = title,
                    noteBody = noteBody,
                    entryDate = LocalDateTime.now()
                )
            )
            title = String()
            noteBody = String()
            showSheet = false
            Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
        } 
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        NoteTextInput(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 0.dp),
            text = title,
            label = stringResource(id = R.string.note_title_input_label)
        ) {
            if (it.all { x -> x.isLetter() || x.isWhitespace() }) { title = it }
        }
        NoteTextInput(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 0.dp),
            text = noteBody,
            label = stringResource(id = R.string.note_body_input_label)
        ) {
            if (it.all { x -> x.isLetter() || x.isWhitespace() }) { noteBody = it }
        }
        Spacer(modifier = Modifier.height(20.dp))
        NoteButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 0.dp),
            text = stringResource(id = R.string.note_save_button)
        ) {
            if (!validNote(title, noteBody)) return@NoteButton
            showSheet = true
        }
        Spacer(modifier = Modifier.height(24.dp))
        LazyColumn {
            items(notes) {
                NoteRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 16.dp),
                    note = it
                ) { note ->
                    onRemoveNote(note)
                }
            }
        }
    }
}

fun validNote(title: String, noteBody: String): Boolean = title.isNotEmpty() && noteBody.isNotEmpty()

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun NoteFragmentPreview() {
    NoteFragment()
}
