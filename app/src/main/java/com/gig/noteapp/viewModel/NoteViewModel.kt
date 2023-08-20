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

    private var _title = MutableStateFlow(String())
    val title by lazy { _title.asStateFlow() }

    private var _body = MutableStateFlow(String())
    val body by lazy { _body.asStateFlow() }

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

    fun setTitleNote(title: String) = viewModelScope.launch(Dispatchers.IO) {
        _title.value = title
    }

    fun setBodyNote(body: String) = viewModelScope.launch(Dispatchers.IO) {
        _body.value = body
    }

    fun validateTitleNote(title: String, onError:() -> Unit = {}, onSuccess: () -> Unit = {}) {
        if(title.all { x -> x.isLetterOrDigit() || x.isWhitespace()}) {
            onSuccess()
        } else {
            onError()
        }
    }

    fun validateBodyNote(body: String, onError: () -> Unit = {}, onSuccess: () -> Unit = {}) {
        if(body.isNotEmpty()) {
            onSuccess()
        } else {
            onError()
        }
    }

}