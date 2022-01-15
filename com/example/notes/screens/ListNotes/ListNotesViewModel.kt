package com.example.notes.screens.ListNotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notes.App
import com.example.notes.models.Note
import com.example.notes.repository.Repository

class ListNotesViewModel: ViewModel() {
    var repository = Repository.getRepository()

    val noteLiveData: LiveData<List<Note>>? = repository.noteLiveData
}