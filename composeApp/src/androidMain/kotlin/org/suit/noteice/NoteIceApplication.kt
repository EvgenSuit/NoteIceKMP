package org.suit.noteice

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.suit.noteice.koin.setupKoin

class NoteIceApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        setupKoin {
            androidContext(this@NoteIceApplication)
        }
    }
}