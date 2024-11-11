package com.example.makemynotes.repository

import androidx.lifecycle.LiveData
import com.example.makemynotes.database.Notes
import com.example.makemynotes.database.NotesDao

class NotesRepository(private val notesDao: NotesDao) {

    fun getAllNotes(userId: String): LiveData<List<Notes>> {
        return notesDao.getAllNotes(userId)
    }

    fun searchNotes(query: String, userId: String): LiveData<List<Notes>> {
        return notesDao.searchNotes("%$query%", userId)
    }

    suspend fun insertNote(note: Notes) {
        notesDao.insertNote(note)
    }

    suspend fun deleteNote(note: Notes) {
        notesDao.deleteNote(note)
    }

    suspend fun deleteAllNotes(userId: String) {
        notesDao.deleteAllNote(userId)
    }

    suspend fun updateNoteColor(note: Notes) {
        notesDao.updateNoteColor(note)
    }

    suspend fun updateNote(note: Notes) {
        notesDao.updateNote(note)
    }

    fun getNoteById(noteId: Int): LiveData<Notes> {
        return notesDao.getNoteById(noteId)
    }
}