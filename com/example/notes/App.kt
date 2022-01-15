package com.example.notes

import android.app.Application
import android.util.Log
import com.example.notes.database.AppDatabase
import com.example.notes.database.NoteDao

class App: Application() {

    companion object{
        var database: AppDatabase? = null
        var noteDao: NoteDao? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getDatabase(applicationContext)
        noteDao = database?.noteDao()
    }
}