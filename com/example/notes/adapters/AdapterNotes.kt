package com.example.notes.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.ItemNoteBinding
import com.example.notes.interfaces.ItemTouchHelperAdapter
import com.example.notes.interfaces.RemoveItemAdapter
import com.example.notes.models.Note

class AdapterNotes(private val list: MutableList<Note>, val callbackItem: RemoveItemAdapter): RecyclerView.Adapter<AdapterNotes.ViewHolder>(), ItemTouchHelperAdapter {

    private val callback = callbackItem

    class ViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.textViewNote.text = note.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = list.get(position)
        holder.bind(note)
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id", note.uid)
            Navigation.findNavController(holder.itemView).navigate(R.id.detailsFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemDismiss(position: Int) {
        callback.removePosition(list.get(position))
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}