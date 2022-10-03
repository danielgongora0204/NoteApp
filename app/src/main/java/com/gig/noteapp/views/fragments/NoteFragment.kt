package com.gig.noteapp.views.fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gig.noteapp.models.ui.AppBarState
import com.gig.noteapp.navigation.NoteViews

@ExperimentalMaterial3Api
@Composable
fun NoteFragment(modifier: Modifier = Modifier, navController: NavController? = null, onComposing: (AppBarState) -> Unit = {}) {
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
    Column(modifier = modifier.padding(6.dp)) {
        Text(text = "Hello from my app")
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun NoteFragmentPreview() {
    NoteFragment()
}
