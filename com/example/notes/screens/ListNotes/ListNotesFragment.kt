package com.example.notes.screens.ListNotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.notes.MainActivity
import com.example.notes.R
import com.example.notes.adapters.AdapterNotes
import com.example.notes.databinding.FragmentListNotesBinding
import com.example.notes.helpers.NoteDiffUtilCallback
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
        var adapter = AdapterNotes(removeItemAdapter)
        viewModel.noteLiveData?.observe(viewLifecycleOwner, Observer {
            var noteDiffUtilCallback = NoteDiffUtilCallback(adapter.getData(), it)
            var noteDiffResult = DiffUtil.calculateDiff(noteDiffUtilCallback)
            adapter.setData(it)
            noteDiffResult.dispatchUpdatesTo(adapter)
            var callback: ItemTouchHelper.Callback = SimpleItemTouchHelperCallback(adapter)
            var touchHelper = ItemTouchHelper(callback)
            touchHelper.attachToRecyclerView(binding.rvList)
            binding.rvList.adapter = adapter
        })
        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.detailsFragment)
        }
        (activity as MainActivity).supportActionBar?.title = getString(R.string.actionbar_title)

        return binding.root
    }


}