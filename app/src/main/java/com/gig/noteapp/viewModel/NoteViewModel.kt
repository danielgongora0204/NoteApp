package com.gig.noteapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gig.noteapp.data.repositories.NoteRepository
import com.gig.noteapp.models.database.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    private var _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes by lazy { _notes.asStateFlow() }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNotes().distinctUntilChanged().collect{
                _notes.value = it
            }
        }
    }

    fun addNote(note: Note) = viewModelScope.launch {
        repository.add(note)
    }

    fun removeNote(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

}