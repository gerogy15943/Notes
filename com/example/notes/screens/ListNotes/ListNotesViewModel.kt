package com.example.notes.screens.ListNotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.App
import com.example.notes.models.Note
import com.example.notes.repository.Repository
import kotlinx.coroutines.launch

class ListNotesViewModel: ViewModel() {
    var repository = Repository.getRepository()

    val noteLiveData: LiveData<List<Note>>? = repository.noteLiveData

    fun delete(note: Note){
        viewModelScope.launch {
        repository.delete(note)
        }
    }
}