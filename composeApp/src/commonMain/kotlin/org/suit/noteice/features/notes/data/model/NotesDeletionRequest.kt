package org.suit.noteice.features.notes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NotesDeletionRequest(
    val ids: List<Long>
)
