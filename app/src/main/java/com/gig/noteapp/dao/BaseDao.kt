package com.gig.noteapp.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(vararg values: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg values: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<T>)

    @Delete
    suspend fun delete(vararg values: T)
}