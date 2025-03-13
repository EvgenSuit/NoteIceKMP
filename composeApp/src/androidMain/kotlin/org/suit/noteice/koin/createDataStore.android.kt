package org.suit.noteice.koin

import android.content.Context
import androidx.datastore.core.Serializer

fun <T> createDataStore(
    serializer: Serializer<T>,
    context: Context) = createDataStore(
    serializer = serializer
) {
    context.filesDir.resolve(datastorePath)
}