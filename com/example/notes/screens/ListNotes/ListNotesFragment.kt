package com.example.notes.screens.ListNotes

import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.notes.R
import com.example.notes.adapters.AdapterNotes
import com.example.notes.databinding.FragmentListNotesBinding
import com.example.notes.helpers.SimpleItemTouchHelperCallback
import com.example.notes.interfaces.RemoveItemAdapter
import com.example.notes.models.Note
import java.text.FieldPosition


class ListNotesFragment : Fragment() {

    lateinit var binding: FragmentListNotesBinding
    lateinit var viewModel: ListNotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val removeItemAdapter = object: RemoveItemAdapter{
            override fun removePosition(note: Note) {
                viewModel.delete(note)
            }
        }
        binding = FragmentListNotesBinding.inflate(layoutInflater, container, false)
        binding.rvList.layoutManager = GridLayoutManager(context, 2)
        viewModel = ViewModelProvider(this).get(ListNotesViewModel::class.java)
        viewModel.noteLiveData?.observe(viewLifecycleOwner, Observer {
            val adapter = AdapterNotes(it as MutableList<Note>, removeItemAdapter)
            val callback: ItemTouchHelper.Callback = SimpleItemTouchHelperCallback(adapter)
            val touchHelper = ItemTouchHelper(callback)
            touchHelper.attachToRecyclerView(binding.rvList)
            binding.rvList.adapter = adapter
        })
        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.detailsFragment)
        }
        return binding.root
    }


}