package org.suit.noteice.utils.ktor

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class InputFieldError(
    val login: String? = null,
    val password: String? = null
)