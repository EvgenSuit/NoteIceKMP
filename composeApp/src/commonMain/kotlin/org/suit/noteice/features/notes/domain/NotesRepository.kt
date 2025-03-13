package org.suit.noteice.features.notes.domain

import org.suit.noteice.features.notes.Note

interface NotesRepository {
    suspend fun deleteNotes(notes: List<Note>)
    suspend fun editNote(note: Note): Note
    suspend fun saveNote(note: Note): Note
    suspend fun fetchNotes(): List<Note>?
}