package org.suit.noteice.features.notes.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.suit.noteice.theme.NoteIceTheme

@Composable
fun SelectNotesBar(
    selectAll: Boolean,
    onSelectAll: (Boolean) -> Unit,
    onDelete: () -> Unit
) {
    val scale = Modifier.scale(1.5f)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = { onSelectAll(false) }) {
            val icon = Icons.AutoMirrored.Filled.ArrowBack
            Icon(
                imageVector = icon,
                contentDescription = icon.name,
                modifier = scale
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = onDelete) {
            val icon = Icons.Filled.Delete
            Icon(
                imageVector = icon,
                tint = MaterialTheme.colorScheme.error,
                contentDescription = icon.name,
                modifier = scale
            )
        }
        Checkbox(
            checked = selectAll,
            onCheckedChange = onSelectAll,
            modifier = scale
        )
    }
}

@Preview
@Composable
fun SelectNotesBarPreview() {
    NoteIceTheme {
        Surface {
            SelectNotesBar(selectAll = true,
                onSelectAll = {},
                onDelete = {})
        }
    }
}