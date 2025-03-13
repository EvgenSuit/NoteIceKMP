package org.suit.noteice.features.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import noteice.composeapp.generated.resources.Res
import noteice.composeapp.generated.resources.could_not_sign_in
import noteice.composeapp.generated.resources.could_not_sign_in_check_credentials
import noteice.composeapp.generated.resources.could_not_sign_up
import noteice.composeapp.generated.resources.email_confirmation
import noteice.composeapp.generated.resources.user_already_exists
import org.suit.noteice.features.auth.domain.AuthRepository
import org.suit.noteice.utils.CustomResult
import org.suit.noteice.utils.ktor.InputFieldError
import org.suit.noteice.utils.ktor.InputFieldException
import org.suit.noteice.utils.ktor.SignInException
import org.suit.noteice.utils.ktor.UserAlreadyExistsException
import org.suit.noteice.utils.ui.AuthUIEvent
import org.suit.noteice.utils.ui.UIText

class AuthViewModel(
    private val authRepository: AuthRepository
): ViewModel() {
    private val inputErrorEncoder = Json {ignoreUnknownKeys = true}
    private val _uiState = MutableStateFlow(AuthUIState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvents = MutableSharedFlow<AuthUIEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    fun handleIntent(intent: AuthIntent) {
        when (intent) {
            is AuthIntent.ChangeAuthType -> _uiState.update { it.copy(
                authType = if (it.authType is AuthType.SignIn) AuthType.SignUp else AuthType.SignIn,
                inputError = null
            ) }
            is AuthIntent.Authenticate -> when(_uiState.value.authType) {
                is AuthType.SignUp -> signUp(intent.email, intent.password)
                is AuthType.SignIn -> signIn(intent.email, intent.password)
            }
        }
    }

    private fun signUp(email: String, password: String) {
        viewModelScope.launch {
            updateAuthResult(CustomResult.InProgress)
            _uiState.update { it.copy(inputError = null) }
            try {
                authRepository.signUp(email, password)
                handleIntent(AuthIntent.ChangeAuthType)
                _uiEvents.emit(AuthUIEvent.ShowSnackbar(UIText.StringResource(Res.string.email_confirmation)))
                updateAuthResult(CustomResult.Success)
            } catch (e: Exception) {
                when (e) {
                    is InputFieldException ->
                        _uiState.update { it.copy(inputError = inputErrorEncoder.decodeFromString(e.message!!)) }
                    is UserAlreadyExistsException -> _uiEvents.emit(AuthUIEvent.ShowSnackbar(UIText.StringResource(Res.string.user_already_exists)))
                    else -> _uiEvents.emit(AuthUIEvent.ShowSnackbar(UIText.StringResource(Res.string.could_not_sign_up)))
                }
                updateAuthResult(CustomResult.Error)
            }
        }
    }

    private fun signIn(email: String, password: String) {
        viewModelScope.launch {
            updateAuthResult(CustomResult.InProgress)
            _uiState.update { it.copy(inputError = null) }
            //try {
                authRepository.signIn(email, password)
                updateAuthResult(CustomResult.Success)
                _uiEvents.emit(AuthUIEvent.NavigateToNotesScreen)
            /*} catch (e: Exception) {
                println(e)
                when (e) {
                    is InputFieldException ->
                        _uiState.update { it.copy(inputError = inputErrorEncoder.decodeFromString(e.message!!)) }
                    is SignInException -> _uiEvents.emit(AuthUIEvent.ShowSnackbar(UIText.StringResource(Res.string.could_not_sign_in_check_credentials)))
                    else -> _uiEvents.emit(AuthUIEvent.ShowSnackbar(UIText.StringResource(Res.string.could_not_sign_in)))
                }
                updateAuthResult(CustomResult.Error)
            }*/
        }
    }

    private fun updateAuthResult(result: CustomResult) =
        _uiState.update { it.copy(authResult = result) }
}

data class AuthUIState(
    val authType: AuthType = AuthType.SignIn,
    val inputError: InputFieldError? = null,
    val authResult: CustomResult = CustomResult.None
)