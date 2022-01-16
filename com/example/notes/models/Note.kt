package com.example.notes.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(

    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo(name = "text")
    var text: String,
    @ColumnInfo(name = "time")
    val time: Long,
    @ColumnInfo(name = "done")
    val done: Boolean){


}
