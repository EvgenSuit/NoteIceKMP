package org.suit.noteice.utils.notes.domain

import org.suit.noteice.features.notes.Note
import org.suit.noteice.features.notes.data.model.NoteRequest
import org.suit.noteice.features.notes.data.model.NotesDeletionRequest

interface NotesClient {
    suspend fun deleteNotes(notesDeletionRequest: NotesDeletionRequest)
    suspend fun editNote(id: Long, noteRequest: NoteRequest): Note
    suspend fun saveNote(noteRequest: NoteRequest): Note
    suspend fun getNotes(): List<Note>?
}