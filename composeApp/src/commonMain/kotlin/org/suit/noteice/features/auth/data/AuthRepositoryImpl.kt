package org.suit.noteice.features.auth.data

import org.suit.noteice.features.auth.data.model.AuthRequest
import org.suit.noteice.features.auth.domain.AuthClient
import org.suit.noteice.features.auth.domain.AuthRepository

class AuthRepositoryImpl(
    private val authClient: AuthClient
): AuthRepository {
    override suspend fun signUp(email: String, password: String)
            = authClient.signUp(AuthRequest(email, password))

    override suspend fun signIn(email: String, password: String)
            = authClient.signIn(AuthRequest(email, password))

    override suspend fun signOut() = authClient.signOut()
}