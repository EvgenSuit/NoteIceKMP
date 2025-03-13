package org.suit.noteice.utils.security.data

import org.suit.noteice.utils.security.domain.CryptoInterface
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

actual object Crypto: CryptoInterface {
    private const val KEY_ALIAS = "secret"
    private const val ALGORITHM = "AES"
    // In Java, PKCS5Padding is used for both PKCS#5 and PKCS#7.
    private const val TRANSFORMATION = "$ALGORITHM/CBC/PKCS5Padding"

    private fun generateKey(): SecretKey {
        return KeyGenerator.getInstance(ALGORITHM)
            .generateKey()
    }
    private val secretKey: SecretKey = generateKey()


    private fun getCipher(): Cipher = Cipher.getInstance(TRANSFORMATION)

    override fun encrypt(bytes: ByteArray): ByteArray {
        val cipher = getCipher()
        println("Encrypted. ${secretKey.encoded}")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val iv = cipher.iv
        val encrypted = cipher.doFinal(bytes)
        return iv + encrypted
    }

    override fun decrypt(bytes: ByteArray): ByteArray {
        val cipher = getCipher()
        val iv = bytes.copyOfRange(0, cipher.blockSize)
        val data = bytes.copyOfRange(cipher.blockSize, bytes.size)
        println("Decrypt. ${secretKey.encoded}")
        cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(iv))
        return cipher.doFinal(data)
    }
}