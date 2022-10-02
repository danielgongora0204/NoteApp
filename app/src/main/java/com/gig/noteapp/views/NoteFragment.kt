package com.gig.noteapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@ExperimentalMaterial3Api
@Composable
fun NoteFragment(modifier: Modifier = Modifier, navController: NavController? = null) {
    Column(modifier = modifier.padding(6.dp)){
        Text(text = "Hello from my app")
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun NoteFragmentPreview() {
    NoteFragment()
}