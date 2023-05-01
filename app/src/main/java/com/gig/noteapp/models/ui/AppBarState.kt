package com.gig.noteapp.models.ui

import androidx.compose.runtime.Composable

data class AppBarState(
    val title: String,
    val actions: (@Composable () -> Unit)?,
    val navigationIcon: (@Composable () -> Unit)?
) {
    class Builder {
        private var title: String = ""
        private var actions: (@Composable () -> Unit)? = null
        private var navigationIcon: (@Composable () -> Unit)? = null

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun setActions(actions: (@Composable () -> Unit)?): Builder {
            this.actions = actions
            return this
        }

        fun setNavigationIcon(navigationIcon: (@Composable () -> Unit)?): Builder {
            this.navigationIcon = navigationIcon
            return this
        }

        fun build(): AppBarState {
            return AppBarState(title, actions, navigationIcon)
        }
    }
}

fun appBarState(
    title: String = "",
    actions: (@Composable () -> Unit)? = null,
    navigationIcon: (@Composable () -> Unit)? = null
): AppBarState {
    return AppBarState.Builder()
        .setTitle(title)
        .setActions(actions)
        .setNavigationIcon(navigationIcon)
        .build()
}