package org.suit.noteice.features.notes

import kotlinx.serialization.Serializable
import org.suit.noteice.features.notes.data.InstantSerializer
import java.time.Instant

@Serializable
data class Note(
    val id: Long = 0,
    val title: String = "",
    val content: String = "",
    @Serializable(with = InstantSerializer::class) val createdAt: Instant? = null,
    @Serializable(with = InstantSerializer::class) val lastModifiedAt: Instant? = null
)