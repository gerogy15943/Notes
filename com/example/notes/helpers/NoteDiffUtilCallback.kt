package com.example.notes.helpers

import androidx.recyclerview.widget.DiffUtil
import com.example.notes.models.Note

class NoteDiffUtilCallback(oldList: List<Note>, newList: List<Note>): DiffUtil.Callback() {

    private var oldList: List<Note> = oldList
    private var newList: List<Note> = newList

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int{
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNotes = oldList.get(oldItemPosition)
        val newNotes = newList.get(newItemPosition)

        return oldNotes.uid == newNotes.uid
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNotes = oldList.get(oldItemPosition)
        val newNotes = newList.get(newItemPosition)

        return oldNotes == newNotes
    }
}