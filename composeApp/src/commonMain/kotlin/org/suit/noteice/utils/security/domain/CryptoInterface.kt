package org.suit.noteice.utils.security.domain

interface CryptoInterface {
    fun encrypt(bytes: ByteArray): ByteArray
    fun decrypt(bytes: ByteArray): ByteArray
}