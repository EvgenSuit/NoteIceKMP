package org.suit.noteice.features.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import org.suit.noteice.features.auth.presentation.ui.AuthScreen
import org.suit.noteice.features.notes.presentation.ui.NotesScreen

@Serializable
data object AuthDestination
@Serializable
data object NotesDestination

@Composable
fun NavManager(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AuthDestination,
        modifier = modifier
    ) {
        composable<AuthDestination> {
            AuthScreen(
                onNavigateToNotesScreen = {
                    navController.navigate(NotesDestination) {
                        popUpTo(navController.graph.id)
                    }
                }
            )
        }
        composable<NotesDestination> {
            NotesScreen(
                onNavigateToAuth = {
                    navController.navigate(AuthDestination) {
                        popUpTo(navController.graph.id)
                    }
                }
            )
        }
    }
}