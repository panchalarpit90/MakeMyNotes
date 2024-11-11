package com.example.makemynotes.ui.notes

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.makemynotes.R
import com.example.makemynotes.database.NotesDatabase
import com.example.makemynotes.databinding.FragmentNotesBinding
import com.example.makemynotes.utils.NotesAdapter
import androidx.lifecycle.Observer
import com.example.makemynotes.repository.NotesRepository
import com.google.firebase.auth.FirebaseAuth


class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesBinding
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var adapter: NotesAdapter
    private lateinit var repository: NotesRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        val sharedPreferences =
            requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val uid = sharedPreferences.getString("USER_UID", "") ?: ""
        val notesDao = NotesDatabase.getInstance(requireContext()).notesDao()
        repository = NotesRepository(notesDao)
        val notesViewModelFactory = NotesViewModelFactory(repository, uid)
        notesViewModel = ViewModelProvider(this, notesViewModelFactory)[NotesViewModel::class.java]
        adapter = NotesAdapter { note ->
            notesViewModel.onNoteClicked(note)
        }

        binding.noteRecycler.adapter = adapter

        notesViewModel.allNotes.observe(viewLifecycleOwner, Observer { notes ->
            adapter.submitList(notes)
            if (notes.isEmpty()) {
                binding.imageView.visibility = View.VISIBLE
            } else {
                binding.imageView.visibility = View.GONE
            }
        })

        notesViewModel.navigateToSelectedNote.observe(viewLifecycleOwner, Observer { note ->
            note?.let {
                this.findNavController()
                    .navigate(NotesFragmentDirections.actionNotesFragmentToEditNotesFragment(note.id))
                notesViewModel.displayPropertyDetailsComplete()
            }
        })

        binding.newNoteFAB.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_editNotesFragment)
        }

        binding.deleteAllBtn.setOnClickListener {
            notesViewModel.deleteALlNotes()
        }

        binding.logoutBtn.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        binding.searchTextView.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchText(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        return binding.root
    }

    private fun searchText(query: String) {
        notesViewModel.searchNotes(query).observe(viewLifecycleOwner, Observer { notes ->
            adapter.submitList(notes)
        })
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Logout")
            .setMessage("Are you sure you want to log out?")
            .setPositiveButton("Yes") { dialog, which ->
                FirebaseAuth.getInstance().signOut()
                findNavController().navigate(R.id.action_notesFragment_to_loginFragment)
            }
            .setNegativeButton("No", null)

        val dialog = builder.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            .setTextColor(resources.getColor(R.color.darkRed))
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            .setTextColor(resources.getColor(R.color.greyText))
    }
}