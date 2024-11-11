package com.example.makemynotes.ui.editnotes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.makemynotes.database.NotesDatabase
import com.example.makemynotes.databinding.FragmentEditNotesBinding
import com.example.makemynotes.repository.NotesRepository


class EditNotesFragment : Fragment() {
    private lateinit var binding: FragmentEditNotesBinding
    private lateinit var editNotesViewModel: EditNotesViewModel
    private lateinit var repository: NotesRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditNotesBinding.inflate(inflater, container, false)

        val sharedPreferences =
            requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val uid = sharedPreferences.getString("USER_UID", "") ?: ""

        val noteId = arguments?.getInt("noteId")
        val dao = NotesDatabase.getInstance(requireContext()).notesDao()
        repository = NotesRepository(dao)
        noteId?.let {
            dao.getNoteById(it).observe(viewLifecycleOwner) { note ->
                val editNotesViewModelFactory = EditNotesViewModelFactory(repository, uid, note)
                editNotesViewModel = ViewModelProvider(
                    this,
                    editNotesViewModelFactory
                )[EditNotesViewModel::class.java]
                binding.viewModel = editNotesViewModel
                binding.lifecycleOwner = viewLifecycleOwner
            }
        }

        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.deleteBtn.setOnClickListener {
            editNotesViewModel.deleteNote()
            findNavController().navigateUp()
        }
        binding.saveFAB.setOnClickListener {
            val title = binding.titleEdittext.text.toString()
            val description = binding.descEditText.text.toString()

            if (title.isBlank() && description.isBlank()) {
                Toast.makeText(requireContext(), "Title and Note are empty", Toast.LENGTH_SHORT)
                    .show()
            } else {
                editNotesViewModel.saveNote(title, description)
                findNavController().navigateUp()
            }
        }

        return binding.root
    }
}
