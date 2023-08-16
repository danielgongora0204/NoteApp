package com.gig.noteapp.data

import androidx.room.TypeConverter
import java.util.Date
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
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}