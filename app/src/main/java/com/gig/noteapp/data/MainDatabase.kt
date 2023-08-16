package com.gig.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gig.noteapp.dao.NoteDao
import com.gig.noteapp.models.database.Note

@Database(
    entities = [Note::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MainDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}