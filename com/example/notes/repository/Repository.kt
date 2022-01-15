package com.example.notes.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.notes.App
import com.example.notes.database.AppDatabase
import com.example.notes.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Repository {
    val noteLiveData: LiveData<List<Note>>? = App.noteDao?.getAllLiveData()

    suspend fun insert(note: Note) = withContext(Dispatchers.IO){
            App.noteDao?.insert(note)
    }

    suspend fun findById(id: Int) = withContext(Dispatchers.IO){
        App.noteDao?.findById(id)
    }

    companion object{
        var instance: Repository? = null

        fun getRepository(): Repository{
            if(instance == null){
                instance = Repository()
            }

            return instance as Repository
        }
    }
}