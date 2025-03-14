package org.suit.noteice.features.auth.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.suit.noteice.features.auth.data.model.AuthRequest
import org.suit.noteice.features.auth.domain.AuthClient
import org.suit.noteice.utils.ktor.BASE_AUTH_URL
import org.suit.noteice.utils.ktor.InputFieldError
import org.suit.noteice.utils.ktor.InputFieldException
import org.suit.noteice.utils.ktor.KtorConstants
import org.suit.noteice.utils.ktor.SignInException
import org.suit.noteice.utils.ktor.UserAlreadyExistsException
import org.suit.noteice.utils.security.data.TokenData
import org.suit.noteice.utils.security.data.TokensManager
import java.util.Locale

class AuthClientImpl(
    engine: HttpClientEngine,
    private val tokensManager: TokensManager,
    private val locale: Locale,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): AuthClient {
    private val inputFieldErrorEncoder = Json { ignoreUnknownKeys = true }
    private val httpClient = HttpClient(engine) {
        expectSuccess = false
        install(Logging)
        install(ContentNegotiation) {
            json()
        }
        HttpResponseValidator {
            validateResponse { response ->
                when (response.status.value) {
                    HttpStatusCode.BadRequest.value -> {
                        val message = response.body<InputFieldError>()
                        throw InputFieldException(inputFieldErrorEncoder.encodeToString(message))
                    }
                    HttpStatusCode.Conflict.value -> {
                        throw UserAlreadyExistsException()
                    }
                    HttpStatusCode.Unauthorized.value -> {
                        //throw SignInException()
                    }
                    in 300..399 -> throw RedirectResponseException(response, "")
                    in 400..499 -> throw ClientRequestException(response, "")
                    in 500..599 -> throw ServerResponseException(response, "")
                }
            }
        }
        defaultRequest {
            url(BASE_AUTH_URL)
        }
    }

    override suspend fun signUp(authRequest: AuthRequest) {
        withContext(dispatcher) {
            httpClient.post("signup") {
                contentType(ContentType.Application.Json)
                headers {
                    headersOf(HttpHeaders.AcceptLanguage, locale.language)
                }
                setBody(authRequest)
            }
        }
    }

    override suspend fun signIn(authRequest: AuthRequest) {
        withContext(dispatcher) {
            val tokenData = httpClient.post("signin") {
                contentType(ContentType.Application.Json)
                headers {
                    headersOf(HttpHeaders.AcceptLanguage, locale.language)
                }
                setBody(authRequest)
            }
            tokensManager.saveTokenData(tokenData.body<TokenData>())
        }
    }

    override suspend fun signOut() {
        withContext(dispatcher) {
            httpClient.post("logout")
            tokensManager.clearTokenData()
        }
    }
}