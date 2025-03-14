package org.suit.noteice.utils.security.data

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import org.suit.noteice.utils.security.data.Crypto
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import java.util.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Serializable
data class TokenData(
    val accessToken: String? = null,
    val refreshToken: String? = null
)
object TokenDataSerializer: Serializer<TokenData> {
    override val defaultValue: TokenData
        get() = TokenData()

    override suspend fun readFrom(input: InputStream): TokenData = withContext(Dispatchers.IO) {
        val encryptedBytes = input.use { it.readBytes() }
        val encryptedBytesDecoded = Base64.getDecoder().decode(encryptedBytes)
        val decryptedBytes = Crypto.decrypt(encryptedBytesDecoded)
        val decodedJsonString = decryptedBytes.decodeToString()
        Json.decodeFromString(decodedJsonString)
    }

    override suspend fun writeTo(t: TokenData, output: OutputStream) = withContext(Dispatchers.IO) {
        val json = Json.encodeToString(t)
        val bytes = json.toByteArray()
        val encryptedBytes = Crypto.encrypt(bytes)
        // avoid "certain" crashes by encoding bytes to base64
        val encryptedBytesBase64 = Base64.getEncoder().encode(encryptedBytes)
        output.use {
            it.write(encryptedBytesBase64)
        }
    }
}
