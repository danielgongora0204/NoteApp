package com.gig.noteapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gig.noteapp.models.database.noteListSaver
import com.gig.noteapp.models.ui.AppBarState
import com.gig.noteapp.views.fragments.NoteFragment

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun NoteNavigation(modifier: Modifier = Modifier, navController: NavHostController? = null, onComposing: (AppBarState) -> Unit = {}) {
    val controller = navController ?: rememberNavController()
    NavHost(navController = controller, startDestination = NoteViews.NoteFragment.name) {
        composable(NoteViews.NoteFragment.name) {
            val notes = rememberSaveable(
                saver = noteListSaver
            ) { mutableStateListOf() }
            NoteFragment(
                modifier = modifier,
                navController = navController,
                notes = notes,
                onAddNote = {
                    notes.add(it)
                },
                onRemoveNote = {
                    notes.remove(it)
                }
            ) {
                onComposing(it)
            }
        }
    }
}
