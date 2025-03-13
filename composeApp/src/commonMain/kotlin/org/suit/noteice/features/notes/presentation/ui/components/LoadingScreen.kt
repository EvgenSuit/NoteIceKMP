package org.suit.noteice.features.notes.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import noteice.composeapp.generated.resources.Res
import noteice.composeapp.generated.resources.app_name
import noteice.composeapp.generated.resources.could_not_fetch_notes
import noteice.composeapp.generated.resources.retry
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.suit.noteice.theme.NoteIceTheme
import org.suit.noteice.utils.ui.commonComponents.CommonButton

@Composable
fun LoadingScreen(
    notesFetchFailed: Boolean,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.4f))
        Text(text = stringResource(Res.string.app_name),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(150.dp))
        if (notesFetchFailed) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(Res.string.could_not_fetch_notes),
                    style = MaterialTheme.typography.labelMedium)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    val retryIcon = Icons.Filled.Refresh
                    Icon(imageVector = retryIcon, contentDescription = retryIcon.name)
                    CommonButton(
                        Res.string.retry,
                        onClick = onRetry)
                }
            }
        }
    }
}

@Preview
@Composable
fun LoadingScreenPreview() {
    NoteIceTheme {
        Surface {
            LoadingScreen(
                notesFetchFailed = true,
                onRetry = {})
        }
    }
}