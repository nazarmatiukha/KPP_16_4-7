package com.example.noteapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.noteapp.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    private lateinit var note: Note
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteRepository: NoteRepository
    private val args: ThirdFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        binding.buttonDeleteNote.setOnClickListener {
            deleteNote()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteRepository = NoteRepository(requireContext())
        note = noteRepository.getNoteById(args.noteId)!!
        if (note != null) {
            binding.textViewNoteTitle.text = note.title
            binding.textViewNoteContent.text = note.content
        }
    }
    private fun deleteNote() {

        noteRepository.deleteNote(note)

        requireActivity().supportFragmentManager.popBackStack()

        Toast.makeText(requireContext(), R.string.note_deleted, Toast.LENGTH_SHORT).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}