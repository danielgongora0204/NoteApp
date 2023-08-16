package com.gig.noteapp.data.repositories

import com.gig.noteapp.dao.NoteDao
import com.gig.noteapp.models.database.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
): BaseRepository<Note>(noteDao) {
    fun getNoteById(id: String): Flow<Note> = noteDao.getNoteById(id).flowOn(Dispatchers.IO).conflate()
    fun getNotes(): Flow<List<Note>> = noteDao.getNotes().flowOn(Dispatchers.IO).conflate()
    suspend fun deleteAll() = noteDao.deleteAll()
    fun loadTestingNotes(): List<Note> {
        return listOf(
            Note(title = "Hello", noteBody = "This is my first note"),
            Note(title = "Hello", noteBody = "This is my second note"),
            Note(title = "Hello", noteBody = "This is my third note"),
            Note(title = "Hello", noteBody = "This is my fourth note"),
            Note(title = "Hello", noteBody = "This is my fifth note"),
            Note(title = "Hello", noteBody = "This is my sixth note"),
        )
    }
}
