package com.gig.noteapp.di

import android.content.Context
import androidx.room.Room
import com.gig.noteapp.dao.NoteDao
import com.gig.noteapp.data.MainDatabase
import com.gig.noteapp.helper.contants.DatabaseConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Single source of truth, will not be recreated into various instances.
@InstallIn(SingletonComponent::class)
@Module
object AppModules {
    @Singleton
    @Provides
    fun providesNoteDao(database: MainDatabase): NoteDao = database.noteDao()

    @Singleton
    @Provides
    fun providesMainDatabase(@ApplicationContext context: Context): MainDatabase =
        Room.databaseBuilder(context, MainDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
}