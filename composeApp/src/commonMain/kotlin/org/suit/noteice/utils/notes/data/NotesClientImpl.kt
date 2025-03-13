package org.suit.noteice.utils.notes.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.suit.noteice.features.notes.Note
import org.suit.noteice.features.notes.data.model.NoteRequest
import org.suit.noteice.features.notes.data.model.NotesDeletionRequest
import org.suit.noteice.utils.ktor.BASE_AUTH_URL
import org.suit.noteice.utils.ktor.BASE_NOTES_URL
import org.suit.noteice.utils.ktor.KtorConstants
import org.suit.noteice.utils.ktor.UnauthorizedException
import org.suit.noteice.utils.notes.domain.NotesClient
import org.suit.noteice.utils.security.data.TokenData
import org.suit.noteice.utils.security.data.TokensManager

class NotesClientImpl(
    refreshTokenEngine: HttpClientEngine,
    notesEngine: HttpClientEngine,
    private val tokensManager: TokensManager,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): NotesClient {
    private val refreshTokenHttpClient = HttpClient(refreshTokenEngine) {
        expectSuccess = true
        install(Logging)
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
            })
        }
        defaultRequest {
            url("${BASE_AUTH_URL}refresh")
        }
    }

    private val notesHttpClient = HttpClient(notesEngine) {
        expectSuccess = true
        install(Logging)
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
            })
        }
        defaultRequest {
            url(BASE_NOTES_URL)
        }
    }

    init {
        // bearer token gets appended in every request sent to the server during interception
        notesHttpClient.plugin(HttpSend).intercept { request ->
            val tokens = tokensManager.getSavedTokenData()

            request.headers {
                append("Authorization", "Bearer ${tokens.accessToken!!}")
            }
            val originalCall = execute(request)
            if (originalCall.response.status.value == 401) {
                try {
                    val newTokens = refreshTokenHttpClient.post("") {
                        contentType(ContentType.Application.Json)
                        setBody(RefreshTokenRequest(tokens.refreshToken!!))
                    }.body<TokenData>()
                    tokensManager.saveTokenData(newTokens)
                    request.headers {
                        remove("Authorization")
                        append("Authorization", "Bearer ${newTokens.accessToken!!}")
                    }
                } catch (e: Exception) {
                    // clear tokens so that no network requests will be performed the next time a user enters the app while having invalid tokens
                    tokensManager.clearTokenData()
                    throw UnauthorizedException("Token refresh failed: ${e.message}")
                }
                val newCall = execute(request)
                if (newCall.response.status.value == 401) throw UnauthorizedException("Unauthorized")
                newCall
            } else originalCall
        }
    }

    override suspend fun deleteNotes(notesDeletionRequest: NotesDeletionRequest) {
        withContext(dispatcher) {
            notesHttpClient.delete("delete") {
                setBody(notesDeletionRequest)
                contentType(ContentType.Application.Json)
            }
        }
    }

    override suspend fun editNote(id: Long, noteRequest: NoteRequest) = withContext(dispatcher) {
        notesHttpClient.put("$id") {
            setBody(noteRequest)
            contentType(ContentType.Application.Json)
        }.body<Note>()
    }
    override suspend fun saveNote(noteRequest: NoteRequest) = withContext(dispatcher) {
        notesHttpClient.post("") {
            setBody(noteRequest)
            contentType(ContentType.Application.Json)
        }.body<Note>()
    }

    override suspend fun getNotes(): List<Note>? = withContext(dispatcher) {
        if (tokensManager.getSavedTokenData() == TokenData()) null
        else notesHttpClient.get("").body<List<Note>>()
    }
}