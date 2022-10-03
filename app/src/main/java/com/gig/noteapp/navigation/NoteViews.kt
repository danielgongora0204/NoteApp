package com.gig.noteapp.navigation

import androidx.annotation.StringRes
import com.gig.noteapp.R

enum class NoteViews(@StringRes val title: Int) {
    NoteFragment(R.string.note_fragment_title);

    companion object {
        fun fromRoute(route: String?): NoteViews =
            when (route?.substringBefore("/")) {
                NoteFragment.name -> NoteFragment
                null -> NoteFragment
                else -> throw IllegalArgumentException("Unknown route")
            }
    }
}
