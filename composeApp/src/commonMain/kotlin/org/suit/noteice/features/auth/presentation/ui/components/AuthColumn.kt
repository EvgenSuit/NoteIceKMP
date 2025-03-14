package org.suit.noteice.features.auth.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.unit.dp
import noteice.composeapp.generated.resources.Res
import noteice.composeapp.generated.resources.app_name
import noteice.composeapp.generated.resources.do_not_have_account
import noteice.composeapp.generated.resources.go_to_signup
import noteice.composeapp.generated.resources.sign_in
import noteice.composeapp.generated.resources.sign_up
import org.jetbrains.compose.resources.stringResource
import org.suit.noteice.features.auth.presentation.AuthType
import org.suit.noteice.utils.ktor.InputFieldError
import org.suit.noteice.utils.ui.commonComponents.CommonButton

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthColumn(
    authType: AuthType,
    enabled: Boolean,
    inputError: InputFieldError?,
    onAuth: (String, String) -> Unit,
    onAuthTypeChange: () -> Unit
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    BackHandler(authType is AuthType.SignUp) {
        onAuthTypeChange()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        if (authType is AuthType.SignUp) {
            Box(modifier = Modifier.fillMaxWidth()) {
                val icon = Icons.AutoMirrored.Filled.ArrowBack
                IconButton(onClick = onAuthTypeChange) {
                    Icon(imageVector = icon, contentDescription = icon.name)
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 50.dp))
        Text(text = stringResource(Res.string.app_name),
            style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.padding(top = 150.dp))
        AuthTextField(value = email, authFieldType = AuthFieldType.Email,
            onValueChange = { email = it })
        if (inputError?.login != null) InputErrorComponent(text = inputError.login)

        AuthTextField(value = password, authFieldType = AuthFieldType.Password,
            onValueChange = { password = it })
        if (inputError?.password != null) InputErrorComponent(text = inputError.password)

        Spacer(modifier = Modifier.height(5.dp))
        CommonButton(when(authType) {
                is AuthType.SignIn -> Res.string.sign_in
                is AuthType.SignUp -> Res.string.sign_up
            },
            enabled = enabled && email.isNotBlank() && password.isNotBlank(),
            onClick = { onAuth(email, password) })
        if (authType is AuthType.SignIn) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = stringResource(resource = Res.string.do_not_have_account))
            TextButton(onClick = onAuthTypeChange,
                enabled = enabled) {
                Text(text = stringResource(Res.string.go_to_signup))
            }
        }
    }
}

@Composable
fun InputErrorComponent(text: String) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.error
            ),
            modifier = Modifier.padding(9.dp))
    }
}