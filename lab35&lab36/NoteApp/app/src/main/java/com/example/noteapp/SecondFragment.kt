package com.example.noteapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.noteapp.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteRepository: NoteRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteRepository = NoteRepository(requireContext())

        binding.buttonSaveNote.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        val title = binding.editTextNoteTitle.text.toString()
        val content = binding.editTextNoteContent.text.toString()

        if (title.isNotBlank() && content.isNotBlank()) {
            val note = Note(title = title, content = content)
            noteRepository.saveNote(note)
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        } else {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}