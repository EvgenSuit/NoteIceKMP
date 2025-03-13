package org.suit.noteice.koin

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import org.suit.noteice.utils.security.data.TokenDataSerializer
import org.suit.noteice.utils.security.data.TokensManager

actual val utilsModule = module {
    single {
        TokensManager(
            tokensDataStore = createDataStore(
                serializer = TokenDataSerializer,
                context = androidContext()
            )
        )
    }
}