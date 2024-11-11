package com.example.makemynotes.ui.editnotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.makemynotes.database.Notes
import com.example.makemynotes.repository.NotesRepository

class EditNotesViewModelFactory(
    private val repository: NotesRepository,
    private val userId: String,
    private val note: Notes?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditNotesViewModel::class.java)) {
            return EditNotesViewModel(repository, userId, note) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

