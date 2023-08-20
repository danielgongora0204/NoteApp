package com.gig.noteapp.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String = String(),
    enabled: Boolean = true,
    loading: Boolean = false,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        if(!loading) {
            Text(
                modifier = Modifier.height(height = 32.dp),
                text = text,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            CircularProgressIndicator(
                modifier = Modifier.size(size = 32.dp),
                color = Color.White
            )
        }
    }
}
