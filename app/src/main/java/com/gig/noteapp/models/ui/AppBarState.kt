package com.gig.noteapp.models.ui

import androidx.compose.runtime.Composable

data class AppBarState(
    val title: String = "",
    val actions: (@Composable () -> Unit)? = null,
    val navigationIcon: (@Composable () -> Unit)? = null
)
