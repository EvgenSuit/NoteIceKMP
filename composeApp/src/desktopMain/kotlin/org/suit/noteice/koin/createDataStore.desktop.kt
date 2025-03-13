package org.suit.noteice.koin

import androidx.datastore.core.Serializer
import okio.Path.Companion.toPath
import java.io.File

fun <T> createDataStore(
    serializer: Serializer<T>) = createDataStore(
        serializer = serializer,
        produceFile = {
            File(datastorePath)
        }
    )