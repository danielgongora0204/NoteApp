package com.gig.noteapp.dao

import androidx.room.Dao
import androidx.room.Query
import com.gig.noteapp.models.database.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao: BaseDao<Note> {
    @Query("SELECT * FROM notes_tbl")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes_tbl WHERE id =:id")
    fun getNoteById(id: String): Flow<Note>

    @Query("DELETE FROM notes_tbl")
    suspend fun deleteAll()
}