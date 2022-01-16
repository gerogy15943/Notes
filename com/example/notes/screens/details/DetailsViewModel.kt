package com.example.notes.screens.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.models.Note
import com.example.notes.repository.Repository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailsViewModel: ViewModel() {

    var repository = Repository.getRepository()

    fun insert(note: Note) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    fun update(note: Note) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    suspend fun findById(id: Int?): Note? {
        var note = repository.findById(id)
        return note
    }
}