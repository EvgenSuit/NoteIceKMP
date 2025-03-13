package org.suit.noteice.features.auth.domain

import org.suit.noteice.features.auth.data.model.AuthRequest

interface AuthClient {
    suspend fun signUp(authRequest: AuthRequest)
    suspend fun signIn(authRequest: AuthRequest)
    suspend fun signOut()
}