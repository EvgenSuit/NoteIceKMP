package org.suit.noteice.koin

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import okio.Path.Companion.toPath
import java.io.File

fun <T> createDataStore(
    serializer: Serializer<T>,
    produceFile: () -> File
): DataStore<T> =
    DataStoreFactory.create(
        serializer = serializer,
        produceFile = produceFile
    )

internal const val datastorePath = "crypto.preferences_pb"