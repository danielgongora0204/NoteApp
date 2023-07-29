package com.gig.noteapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
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
            val noteViewModel = viewModel<NoteViewModel>()
            NoteFragment(
                modifier = modifier,
                navController = navController,
                notes = noteViewModel.notes,
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
    }
}
