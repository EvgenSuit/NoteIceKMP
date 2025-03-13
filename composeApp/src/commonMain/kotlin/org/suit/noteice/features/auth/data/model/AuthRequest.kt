package org.suit.noteice.features.auth.data.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class AuthRequest(
    val login: String,
    val password: String
)