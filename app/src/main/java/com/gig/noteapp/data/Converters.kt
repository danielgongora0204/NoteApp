package com.gig.noteapp.data

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

class Converters {

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun uuidFromString(value: String?): UUID? {
        return value?.let { UUID.fromString(it) }
    }

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime?): String? {
        return dateTime?.format(dateTimeFormatter)
    }

    @TypeConverter
    fun toLocalDateTime(dateTimeString: String?): LocalDateTime? {
        return dateTimeString?.let {
            LocalDateTime.parse(it, dateTimeFormatter)
        }
    }

    companion object {
        private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    }
}