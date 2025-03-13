package org.suit.noteice.koin

import io.ktor.client.engine.cio.CIO
import org.koin.core.module.Module
import org.koin.dsl.module
import org.suit.noteice.features.auth.data.AuthClientImpl
import org.suit.noteice.features.auth.data.AuthRepositoryImpl
import org.suit.noteice.features.auth.domain.AuthClient
import org.suit.noteice.features.auth.domain.AuthRepository
import org.suit.noteice.features.auth.presentation.AuthViewModel
import java.util.Locale

val authModule = module {
    single<AuthClient> {
        AuthClientImpl(
            engine = CIO.create(),
            tokensManager = get(),
            locale = Locale.getDefault()
        )
    }
    single<AuthRepository> {
        AuthRepositoryImpl(
            authClient = get()
        )
    }
    factory {
        AuthViewModel(
            authRepository = get()
        )
    }
}