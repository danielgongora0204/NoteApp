package com.gig.noteapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gig.noteapp.models.ui.AppBarState
import com.gig.noteapp.viewModel.NoteViewModel
import com.gig.noteapp.views.fragments.NoteFragment

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun NoteNavigation(modifier: Modifier = Modifier, navController: NavHostController? = null, onComposing: (AppBarState) -> Unit = {}) {
    val controller = navController ?: rememberNavController()
    NavHost(navController = controller, startDestination = NoteViews.NoteFragment.name) {
        composable(NoteViews.NoteFragment.name) {
            ToNoteFragment(modifier = modifier, navController = navController) {
                onComposing(it)
            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun ToNoteFragment(modifier: Modifier = Modifier, navController: NavHostController? = null, onComposing: (AppBarState) -> Unit = {}) {
    val noteViewModel: NoteViewModel = hiltViewModel()
    val noteList = noteViewModel.notes.collectAsState().value
    val noteTitle = noteViewModel.title.collectAsState().value
    val noteBody = noteViewModel.body.collectAsState().value
    val noteToBeEdited = noteViewModel.noteToBeEdited.collectAsState().value
    NoteFragment(
        modifier = modifier,
        navController = navController,
        notes = noteList,
        title = noteTitle,
        body = noteBody,
        noteToBeEdited = noteToBeEdited,
        validateTitleNote = { title, onError, onSuccess ->
            noteViewModel.validateTitleNote(title, onError, onSuccess)
        },
        validateBodyNote = { body, onError, onSuccess ->
            noteViewModel.validateBodyNote(body, onError, onSuccess)
        },
        setTitleNote = {
            noteViewModel.setTitleNote(it)
        },
        setBodyNote = {
            noteViewModel.setBodyNote(it)
        },
        setEditedNote = {
            noteViewModel.setToBeEditedNote(it)
        },
        onAddNote = {
            noteViewModel.addNote(it)
        },
        onRemoveNote = {
            noteViewModel.removeNote(it)
        }
    ) {
        onComposing(it)
    }
}