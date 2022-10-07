package com.gig.noteapp.views.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gig.noteapp.components.NoteTopAppBar
import com.gig.noteapp.models.ui.AppBarState
import com.gig.noteapp.navigation.NoteNavigation
import com.gig.noteapp.ui.theme.NoteAppTheme

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App { modifier, navController, onComposing ->
                NoteNavigation(modifier, navController) {
                    onComposing(it)
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun App(content: @Composable (modifier: Modifier, navController: NavHostController, onComposing: (AppBarState) -> Unit) -> Unit = { _, _, _ -> }) {
    // Create Nav Controller
    val navController = rememberNavController()
   /* TODO: Check if getting current screen from main activity will be helpful, if possible, avoid it
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = NoteViews.valueOf(
        backStackEntry?.destination?.route ?: NoteViews.NoteFragment.name
    )*/
    // AppBarState
    var appBarState by remember {
        mutableStateOf(AppBarState())
    }
    NoteAppTheme {
        Scaffold(
            topBar = {
                NoteTopAppBar(title = appBarState.title, navigationIcon = { appBarState.navigationIcon?.invoke() }, actions = { appBarState.actions?.invoke() })
            }
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                content(modifier = Modifier.padding(top = it.calculateTopPadding()), navController = navController) {
                    appBarState = it
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App { modifier, navController, appBarState ->
        NoteNavigation(modifier, navController, appBarState)
    }
}
