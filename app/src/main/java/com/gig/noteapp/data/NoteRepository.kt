package com.gig.noteapp.data

import com.gig.noteapp.models.database.Note

class NoteRepository {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "Hello", noteBody = "This is my first note"),
            Note(title = "Hello", noteBody = "This is my second note"),
            Note(title = "Hello", noteBody = "This is my third note")
        )
    }
}
