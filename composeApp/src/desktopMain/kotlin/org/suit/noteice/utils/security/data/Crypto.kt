package org.suit.noteice.utils.security.data

import NoteIce.composeApp.BuildConfig
import org.suit.noteice.utils.security.domain.CryptoInterface
import java.io.File
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

actual object Crypto: CryptoInterface {
    private const val KEY_ALIAS = "secret"
    private const val ALGORITHM = "AES"
    private const val KEYSTORE_FILE = "keystore.jks"
    private const val KEYSTORE_PASSWORD = BuildConfig.CRYPTO_KEYSTORE_PASSWORD
    private const val KEY_PASSWORD = BuildConfig.CRYPTO_KEY_PASSWORD
    // In Java, PKCS5Padding is used for both PKCS#5 and PKCS#7.
    private const val TRANSFORMATION = "$ALGORITHM/CBC/PKCS5Padding"

    private val secretKey: SecretKey by lazy {
        val keyStoreFile = File(KEYSTORE_FILE)
        val keyStore = KeyStore.getInstance("JCEKS")
        if (keyStoreFile.exists()) {
            keyStore.load(keyStoreFile.inputStream(), KEYSTORE_PASSWORD.toCharArray())
            val entry = keyStore.getEntry(KEY_ALIAS, KeyStore.PasswordProtection(KEY_PASSWORD.toCharArray()))
            if (entry is KeyStore.SecretKeyEntry) {
                entry.secretKey
            } else {
                generateAndStoreKey(keyStore, keyStoreFile)
            }
        } else {
            keyStore.load(null, null) // initialize a new keystore
            generateAndStoreKey(keyStore, keyStoreFile)
        }
    }

    private fun generateAndStoreKey(keyStore: KeyStore, keyStoreFile: File): SecretKey {
        val newKey = generateKey()
        val entry = KeyStore.SecretKeyEntry(newKey)
        keyStore.setEntry(KEY_ALIAS, entry, KeyStore.PasswordProtection(KEY_PASSWORD.toCharArray()))
        keyStoreFile.outputStream().use {
            keyStore.store(it, KEYSTORE_PASSWORD.toCharArray())
        }
        return newKey
    }

    private fun generateKey(): SecretKey {
        return KeyGenerator.getInstance(ALGORITHM).generateKey()
    }
    private fun getCipher(): Cipher = Cipher.getInstance(TRANSFORMATION)

    override fun encrypt(bytes: ByteArray): ByteArray {
        val cipher = getCipher()
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val iv = cipher.iv
        val encrypted = cipher.doFinal(bytes)
        return iv + encrypted
    }

    override fun decrypt(bytes: ByteArray): ByteArray {
        val cipher = getCipher()
        val iv = bytes.copyOfRange(0, cipher.blockSize)
        val data = bytes.copyOfRange(cipher.blockSize, bytes.size)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(iv))
        return cipher.doFinal(data)
    }
}