package org.suit.noteice.koin

import io.ktor.client.engine.cio.CIO
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.suit.noteice.features.notes.data.NoteTimeFormatter
import org.suit.noteice.features.notes.data.NotesRepositoryImpl
import org.suit.noteice.features.notes.domain.NotesRepository
import org.suit.noteice.features.notes.presentation.NotesViewModel
import org.suit.noteice.utils.notes.data.NotesClientImpl
import org.suit.noteice.utils.notes.domain.NotesClient
import java.time.Clock
import java.util.Locale

private val commonUtilsModule = module {
    includes(utilsModule,
        module {
            single<NotesClient> {
                NotesClientImpl(
                    refreshTokenEngine = CIO.create(),
                    notesEngine = CIO.create(),
                    tokensManager = get()
                )
            }
        })
}


private val commonNotesModule = module {
    single<NotesRepository> {
        NotesRepositoryImpl(
            notesClient = get()
        )
    }
    factory {
        NoteTimeFormatter(
            locale = Locale.getDefault(),
            clock = Clock.systemDefaultZone()
        )
    }
    factory {
        NotesViewModel(
            notesRepository = get(),
            noteTimeFormatter = get()
        )
    }
}

private val appModule = module {
    includes(
        commonUtilsModule,
        authModule,
        commonNotesModule)
}

fun setupKoin(declaration: KoinAppDeclaration = {}) {
    startKoin {
        declaration()
        modules(appModule)
    }
}