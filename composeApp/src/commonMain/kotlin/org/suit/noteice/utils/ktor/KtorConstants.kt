package org.suit.noteice.utils.ktor

import org.suit.noteice.utils.ktor.KtorConstants.ROOT_URL

expect object KtorConstants {
    val ROOT_URL: String
}

val BASE_AUTH_URL = "$ROOT_URL/noteice/auth/"
val BASE_NOTES_URL = "$ROOT_URL/noteice/notes/"