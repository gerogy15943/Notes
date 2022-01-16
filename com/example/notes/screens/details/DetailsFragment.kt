package com.example.notes.screens.details

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.notes.R
import com.example.notes.databinding.FragmentDetailsBinding
import com.example.notes.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    lateinit var viewModel: DetailsViewModel
    private var id: Int? = null
    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        arguments.let { bundle ->
            id = bundle?.getInt("id")
            lifecycleScope.launch(Dispatchers.Main) {
                note = viewModel.findById(id)
                binding.editTextTextMultiLine.setText(note?.text)
            }
        }
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        val text = binding.editTextTextMultiLine.text.toString()
        if (note != null){
            note?.text = text
            viewModel.update(note!!)
        }
        else {
            val note = Note(0, text, Date().time, false)
            viewModel.update(note)
        }
    }
}