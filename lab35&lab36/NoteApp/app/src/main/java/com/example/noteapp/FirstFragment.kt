package com.example.noteapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteRepository: NoteRepository
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteRepository = NoteRepository(requireContext())

        setupRecyclerView()
        loadNotes()

        binding.buttonAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }

    private fun setupRecyclerView() {
        noteAdapter = NoteAdapter { note ->
            val action = FirstFragmentDirections.actionFirstFragmentToThirdFragment(note.id)
            findNavController().navigate(action)
        }
        binding.recyclerViewNotes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun loadNotes() {
        val notes = noteRepository.getNotes()
        noteAdapter.submitList(notes)
    }

    override fun onResume() {
        super.onResume()
        loadNotes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}