package org.suit.noteice.features.notes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NoteRequest(
    val title: String,
    val content: String
)
