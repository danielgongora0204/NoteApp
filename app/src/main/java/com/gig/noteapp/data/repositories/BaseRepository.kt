package com.gig.noteapp.data.repositories

import com.gig.noteapp.dao.BaseDao

open class BaseRepository<T>(
    private val baseDao: BaseDao<T>
) {
    suspend fun add(vararg values: T) = baseDao.insert(*values)

    suspend fun addAll(list: List<T>) = baseDao.insert(list)

    suspend fun update(vararg values: T) = baseDao.update(*values)

    suspend fun delete(vararg values: T) = baseDao.delete(*values)
}