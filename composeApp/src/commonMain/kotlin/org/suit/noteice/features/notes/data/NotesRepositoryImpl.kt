package org.suit.noteice.features.notes.data

import org.suit.noteice.features.notes.Note
import org.suit.noteice.features.notes.data.model.NoteRequest
import org.suit.noteice.features.notes.data.model.NotesDeletionRequest
import org.suit.noteice.features.notes.domain.NotesRepository
import org.suit.noteice.utils.notes.domain.NotesClient

class NotesRepositoryImpl(
    private val notesClient: NotesClient
): NotesRepository {
    override suspend fun deleteNotes(notes: List<Note>) =
        notesClient.deleteNotes(NotesDeletionRequest(notes.map { it.id }))

    override suspend fun editNote(note: Note) =
        notesClient.editNote(note.id, NoteRequest(title = note.title, content = note.content))

    override suspend fun saveNote(note: Note) =
        notesClient.saveNote(NoteRequest(title = note.title, content = note.content))

    override suspend fun fetchNotes() = notesClient.getNotes()
}