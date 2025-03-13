package org.suit.noteice

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.suit.noteice.koin.setupKoin

fun main() = application {
    setupKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "NoteIceKMP",
    ) {
        App()
    }
}

@Preview
@Composable
fun AppPreview() {

}