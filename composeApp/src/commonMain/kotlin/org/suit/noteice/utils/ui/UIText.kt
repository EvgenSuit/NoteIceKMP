package org.suit.noteice.utils.ui

import androidx.annotation.StringRes
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

sealed class UIText {

    data class StringResource(@StringRes val id: org.jetbrains.compose.resources.StringResource, val args: List<Any> = emptyList()) : UIText()
    data class StringValue(val string: String): UIText()

    suspend fun asString() =
        when (this) {
            is StringResource -> getString(id, *args.toTypedArray())
            is StringValue -> string
        }
}
