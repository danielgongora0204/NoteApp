package com.gig.noteapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gig.noteapp.views.NoteFragment

@ExperimentalMaterial3Api
@Composable
fun NoteNavigation(modifier: Modifier = Modifier, navController: NavHostController? = null){
    val controller = navController ?: rememberNavController()
    NavHost(navController = controller, startDestination = NoteViews.NoteFragment.name) {
        composable(NoteViews.NoteFragment.name) {
            NoteFragment(modifier = modifier, navController = navController)
        }
    }
}
