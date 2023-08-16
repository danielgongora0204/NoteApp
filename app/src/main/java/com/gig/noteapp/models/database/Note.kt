package com.gig.noteapp.models.database

import java.util.UUID
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity(tableName = "notes_tbl")
data class Note(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "note_title")
    val title: String,
    @ColumnInfo(name = "note_body")
    val noteBody: String,
    @ColumnInfo(name = "note_entry_date")
    val entryDate: LocalDateTime = LocalDateTime.now()
)