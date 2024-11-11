package com.example.makemynotes.ui.editnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makemynotes.R
import com.example.makemynotes.database.Notes
import com.example.makemynotes.repository.NotesRepository
import kotlinx.coroutines.launch

class EditNotesViewModel(
    private val repository: NotesRepository,
    private val userId: String,
    private val note: Notes?
) : ViewModel() {

    private val _selectedNote = MutableLiveData<Notes?>()
    val selectedNote: LiveData<Notes?> = _selectedNote

    init {
        _selectedNote.value = note
    }

    fun saveNote(title: String, description: String) {
        viewModelScope.launch {
            val noteToSave = note ?: Notes(
                title = title,
                description = description,
                userId = userId,
                color = getRandomColor()
            )
                .also { repository.insertNote(it) }

            noteToSave.title = title
            noteToSave.description = description
            repository.updateNote(noteToSave)
        }
    }

    fun deleteNote() {
        viewModelScope.launch {
            note?.let { repository.deleteNote(it) }
        }
    }

    private fun getRandomColor(): Int {
        val colors = listOf(
            R.color.blueCard,
            R.color.purpleCard,
            R.color.midnightGreen,
            R.color.slateBlue,
            R.color.smokyLavender,
            R.color.sageGreen,
            R.color.burntUmber,
            R.color.fadedMoss,
            R.color.oceanBlue,
            R.color.clayBrown,
            R.color.stormGray,
            R.color.forestMist
        )
        return colors.random()
    }


}
