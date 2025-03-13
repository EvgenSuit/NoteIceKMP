package org.suit.noteice.utils.security.data

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first
import kotlinx.io.IOException

class TokensManager(
    private val tokensDataStore: DataStore<TokenData>
) {
    suspend fun saveTokenData(data: TokenData) {
        tokensDataStore.updateData {
            data
        }
    }
    suspend fun getSavedTokenData(): TokenData {
        return try {
            tokensDataStore.data.first()
        } catch (e: IOException) {
            TokenData()
        }
    }
    suspend fun clearTokenData() {
        tokensDataStore.updateData { TokenData() }
    }
}