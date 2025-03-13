package org.suit.noteice.utils

sealed class CustomResult {
    data object None: CustomResult()
    data object InProgress: CustomResult()
    data object Success: CustomResult()
    data object Error: CustomResult()

    fun isInProgress() = this is InProgress
}