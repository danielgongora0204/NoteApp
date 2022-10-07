package com.gig.noteapp.models.database

import java.time.LocalDateTime
import java.util.UUID

data class Note constructor(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val noteBody: String,
    val date: LocalDateTime? = LocalDateTime.now()
)
