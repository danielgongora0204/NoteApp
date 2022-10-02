package com.gig.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gig.noteapp.navigation.NoteNavigation
import com.gig.noteapp.navigation.NoteViews
import com.gig.noteapp.ui.theme.NoteAppTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App{ modifier, navController ->
                NoteNavigation(modifier, navController)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun App(content: @Composable (modifier: Modifier, navController: NavHostController) -> Unit = { _, _ -> }) {
    //Create Nav Controller
    val navController = rememberNavController()
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = NoteViews.valueOf(
        backStackEntry?.destination?.route ?: NoteViews.NoteFragment.name
    )

    NoteAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(currentScreen.title))
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent),
                )
            }
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                content(modifier = Modifier.padding(top = it.calculateTopPadding()), navController)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App{ modifier, navController ->
        NoteNavigation(modifier, navController)
    }
}