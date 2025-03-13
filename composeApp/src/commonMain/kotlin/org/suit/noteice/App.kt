package org.suit.noteice

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.suit.noteice.features.navigation.NavManager
import org.suit.noteice.theme.NoteIceTheme
import org.suit.noteice.utils.ui.CustomSnackbar
import org.suit.noteice.utils.ui.LocalSnackbarProvider
import org.suit.noteice.utils.ui.SnackbarController

@Composable
@Preview
fun App() {
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarController by remember(snackbarHostState) {
        mutableStateOf(SnackbarController(
            snackbarHostState = snackbarHostState
        ))
    }
    val focusManager = LocalFocusManager.current
    NoteIceTheme {
        Scaffold(
            snackbarHost = {
                SnackbarHost(snackbarHostState) {
                    CustomSnackbar(
                        message = snackbarHostState.currentSnackbarData?.visuals?.message,
                        onDismiss = { snackbarController.dismiss() }
                    )
                }
            },
            modifier = Modifier
                .pointerInput(Unit) {
                    awaitEachGesture {
                        val upEvent = waitForUpOrCancellation(PointerEventPass.Initial)
                        if (upEvent != null) focusManager.clearFocus(true)
                    }
                }
        ) { innerPadding ->
            CompositionLocalProvider(LocalSnackbarProvider provides snackbarController) {
                NavManager(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .consumeWindowInsets(innerPadding)
                        .imePadding()
                )
            }
        }

    }
}