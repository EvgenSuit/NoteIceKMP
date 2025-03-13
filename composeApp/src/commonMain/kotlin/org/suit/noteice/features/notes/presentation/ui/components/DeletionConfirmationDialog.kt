package org.suit.noteice.features.notes.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import noteice.composeapp.generated.resources.Res
import noteice.composeapp.generated.resources.no
import noteice.composeapp.generated.resources.yes
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeletionConfirmationDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    BasicAlertDialog(onDismissRequest = onDismiss,) {
        Surface {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(25.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Are you sure you want to delete selected notes?",
                    style = MaterialTheme.typography.labelSmall)
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                   TextButton(onClick = onDismiss) {
                       Text(text = stringResource(Res.string.no))
                   }
                    TextButton(onClick = onConfirm) {
                        Text(text = stringResource(Res.string.yes))
                    }
                }
            }
        }
    }
}