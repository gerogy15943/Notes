package com.example.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.models.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object{
        private var instance: AppDatabase? = null


    fun getDatabase(context: Context): AppDatabase?{
        if(instance == null){
            instance = Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "database")
                .build()
        }
        return instance
    }
    }
}