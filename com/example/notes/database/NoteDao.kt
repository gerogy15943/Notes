package com.example.notes.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.models.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Query("SELECT * FROM note")
    fun getAllLiveData(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE uid = :uid LIMIT 1")
    fun findById(uid: Int?): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(notes: Note)
}