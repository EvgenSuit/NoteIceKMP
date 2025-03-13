package org.suit.noteice.features.auth.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import noteice.composeapp.generated.resources.Res
import noteice.composeapp.generated.resources.email
import noteice.composeapp.generated.resources.password
import org.jetbrains.compose.resources.stringResource

sealed class AuthFieldType {
    data object Email: AuthFieldType()
    data object Password: AuthFieldType()
}

@Composable
fun AuthTextField(
    value: String,
    authFieldType: AuthFieldType,
    onValueChange: (String) -> Unit
) {
    var showPassword by rememberSaveable {
        mutableStateOf(false)
    }
    val maxFieldLength = 64
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it.take(maxFieldLength)) },
        singleLine = true,
        maxLines = 1,
        placeholder = {
            Text(text = stringResource(when(authFieldType) {
                is AuthFieldType.Email -> Res.string.email
                is AuthFieldType.Password -> Res.string.password
            }))
        },
        visualTransformation = if ((authFieldType is AuthFieldType.Password &&
            showPassword) || authFieldType is AuthFieldType.Email
        ) VisualTransformation.None
        else PasswordVisualTransformation(),
        trailingIcon = {
            if (authFieldType is AuthFieldType.Password) {
                val icon = if (showPassword) Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(imageVector = icon, contentDescription = icon.name)
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .testTag(authFieldType.toString()))
}