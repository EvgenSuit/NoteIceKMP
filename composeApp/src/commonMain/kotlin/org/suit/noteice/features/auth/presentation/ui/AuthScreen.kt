package org.suit.noteice.features.auth.presentation.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.suit.noteice.features.auth.presentation.AuthIntent
import org.suit.noteice.features.auth.presentation.AuthType
import org.suit.noteice.features.auth.presentation.AuthUIState
import org.suit.noteice.features.auth.presentation.AuthViewModel
import org.suit.noteice.features.auth.presentation.ui.components.AuthColumn
import org.suit.noteice.theme.NoteIceTheme
import org.suit.noteice.utils.CustomResult
import org.suit.noteice.utils.ktor.InputFieldError
import org.suit.noteice.utils.ui.AuthUIEvent
import org.suit.noteice.utils.ui.LocalSnackbarProvider
import org.suit.noteice.utils.ui.commonComponents.ConstrainedColumn

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = koinViewModel(),
    onNavigateToNotesScreen: () -> Unit
) {
    val snackbarController = LocalSnackbarProvider.current
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.uiEvents.collectLatest { event ->
            when (event) {
                is AuthUIEvent.ShowSnackbar -> snackbarController.showSnackbar(event.text)
                is AuthUIEvent.NavigateToNotesScreen -> onNavigateToNotesScreen()
            }
        }
    }
    AuthScreenContent(
        uiState = uiState,
        onIntent = viewModel::handleIntent
    )
}

@Composable
fun AuthScreenContent(
    uiState: AuthUIState,
    onIntent: (AuthIntent) -> Unit
) {
    val authEnabled = uiState.authResult !is CustomResult.InProgress
    ConstrainedColumn(
        modifier = Modifier.padding(10.dp)
    ) {
        Crossfade(targetState = uiState.authType, label = "AuthContentCrossfade") { authType ->
            when (authType) {
                is AuthType.SignIn -> {
                    AuthColumn(
                        authType = authType,
                        enabled = authEnabled,
                        inputError = uiState.inputError,
                        onAuth = { email, password -> onIntent(AuthIntent.Authenticate(email, password)) },
                        onAuthTypeChange = { onIntent(AuthIntent.ChangeAuthType) }
                    )
                }
                is AuthType.SignUp -> {
                    AuthColumn(
                        authType = authType,
                        enabled = authEnabled,
                        inputError = uiState.inputError,
                        onAuth = { email, password -> onIntent(AuthIntent.Authenticate(email, password)) },
                        onAuthTypeChange = { onIntent(AuthIntent.ChangeAuthType) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AuthScreenPreview() {
    NoteIceTheme {
        Surface {
            AuthScreenContent(
                uiState = AuthUIState(
                    inputError = InputFieldError(login = "Invalid login format", password = "Password is too short"),
                    authType = AuthType.SignIn
                ),
                onIntent = {})
        }
    }
}