package com.gig.noteapp.models.database

import java.time.LocalDateTime
import java.util.UUID
import androidx.compose.runtime.saveable.Saver

data class Note constructor(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val noteBody: String,
    val date: LocalDateTime? = LocalDateTime.now()
)

val noteListSaver = Saver<MutableList<Note>, MutableList<UUID>>(
    save = { it.map { note -> note.id }.toMutableList() },
    restore = { it.map { id -> Note(id, "", "") }.toMutableList() }
)