package com.gig.noteapp.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.gig.noteapp.data.NoteRepository
import com.gig.noteapp.models.database.Note

class NoteViewModel : ViewModel() {
    val notes: List<Note> get() = _notes
    private var _notes = mutableStateListOf<Note>()

    init {
        _notes.addAll(NoteRepository().loadNotes())
    }

    fun addNote(note: Note) {
        _notes.add(note)
    }

    fun removeNote(note: Note) {
        _notes.remove(note)
    }
}