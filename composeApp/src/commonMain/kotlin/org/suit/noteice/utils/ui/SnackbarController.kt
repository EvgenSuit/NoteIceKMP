package org.suit.noteice.utils.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.suit.noteice.theme.NoteIceTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

val LocalSnackbarProvider = compositionLocalOf<SnackbarController> {
    error("No SnackbarController provided.")
}

class SnackbarController(
    private val snackbarHostState: SnackbarHostState
) {
    suspend fun showSnackbar(uiText: UIText) {
        val message = uiText.asString()
        if (message.isNotBlank()) {
            dismiss()
            snackbarHostState.showSnackbar(message)
        }
    }
    fun dismiss() = snackbarHostState.currentSnackbarData?.dismiss()
}

@Composable
fun CustomSnackbar(
    message: String?,
    onDismiss: () -> Unit
) {
    message?.let {
        Snackbar(
            action = {
                IconButton(onClick = onDismiss) {
                    val clearIcon = Icons.Filled.Clear
                    Icon(imageVector = clearIcon, contentDescription = clearIcon.name)
                }
            },
            modifier = Modifier
                .padding(horizontal = 20.dp,
                    vertical = 5.dp)
                .consumeWindowInsets(WindowInsets.systemBars)
                .imePadding()
        ) {
            Text(text = it,
                style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Preview
@Composable
fun CustomSnackbarPreview() {
    NoteIceTheme {
        Surface {
            CustomSnackbar(
                message = "Some message",
                onDismiss = {})
        }
    }
}