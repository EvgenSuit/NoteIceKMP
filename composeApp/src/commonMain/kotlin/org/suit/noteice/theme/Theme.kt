package org.suit.noteice.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.suit.noteice.ui.theme.backgroundDark
import com.suit.noteice.ui.theme.backgroundDarkHighContrast
import com.suit.noteice.ui.theme.backgroundDarkMediumContrast
import com.suit.noteice.ui.theme.backgroundLight
import com.suit.noteice.ui.theme.backgroundLightHighContrast
import com.suit.noteice.ui.theme.backgroundLightMediumContrast
import com.suit.noteice.ui.theme.errorContainerDark
import com.suit.noteice.ui.theme.errorContainerDarkHighContrast
import com.suit.noteice.ui.theme.errorContainerDarkMediumContrast
import com.suit.noteice.ui.theme.errorContainerLight
import com.suit.noteice.ui.theme.errorContainerLightHighContrast
import com.suit.noteice.ui.theme.errorContainerLightMediumContrast
import com.suit.noteice.ui.theme.errorDark
import com.suit.noteice.ui.theme.errorDarkHighContrast
import com.suit.noteice.ui.theme.errorDarkMediumContrast
import com.suit.noteice.ui.theme.errorLight
import com.suit.noteice.ui.theme.errorLightHighContrast
import com.suit.noteice.ui.theme.errorLightMediumContrast
import com.suit.noteice.ui.theme.inverseOnSurfaceDark
import com.suit.noteice.ui.theme.inverseOnSurfaceDarkHighContrast
import com.suit.noteice.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.suit.noteice.ui.theme.inverseOnSurfaceLight
import com.suit.noteice.ui.theme.inverseOnSurfaceLightHighContrast
import com.suit.noteice.ui.theme.inverseOnSurfaceLightMediumContrast
import com.suit.noteice.ui.theme.inversePrimaryDark
import com.suit.noteice.ui.theme.inversePrimaryDarkHighContrast
import com.suit.noteice.ui.theme.inversePrimaryDarkMediumContrast
import com.suit.noteice.ui.theme.inversePrimaryLight
import com.suit.noteice.ui.theme.inversePrimaryLightHighContrast
import com.suit.noteice.ui.theme.inversePrimaryLightMediumContrast
import com.suit.noteice.ui.theme.inverseSurfaceDark
import com.suit.noteice.ui.theme.inverseSurfaceDarkHighContrast
import com.suit.noteice.ui.theme.inverseSurfaceDarkMediumContrast
import com.suit.noteice.ui.theme.inverseSurfaceLight
import com.suit.noteice.ui.theme.inverseSurfaceLightHighContrast
import com.suit.noteice.ui.theme.inverseSurfaceLightMediumContrast
import com.suit.noteice.ui.theme.onBackgroundDark
import com.suit.noteice.ui.theme.onBackgroundDarkHighContrast
import com.suit.noteice.ui.theme.onBackgroundDarkMediumContrast
import com.suit.noteice.ui.theme.onBackgroundLight
import com.suit.noteice.ui.theme.onBackgroundLightHighContrast
import com.suit.noteice.ui.theme.onBackgroundLightMediumContrast
import com.suit.noteice.ui.theme.onErrorContainerDark
import com.suit.noteice.ui.theme.onErrorContainerDarkHighContrast
import com.suit.noteice.ui.theme.onErrorContainerDarkMediumContrast
import com.suit.noteice.ui.theme.onErrorContainerLight
import com.suit.noteice.ui.theme.onErrorContainerLightHighContrast
import com.suit.noteice.ui.theme.onErrorContainerLightMediumContrast
import com.suit.noteice.ui.theme.onErrorDark
import com.suit.noteice.ui.theme.onErrorDarkHighContrast
import com.suit.noteice.ui.theme.onErrorDarkMediumContrast
import com.suit.noteice.ui.theme.onErrorLight
import com.suit.noteice.ui.theme.onErrorLightHighContrast
import com.suit.noteice.ui.theme.onErrorLightMediumContrast
import com.suit.noteice.ui.theme.onPrimaryContainerDark
import com.suit.noteice.ui.theme.onPrimaryContainerDarkHighContrast
import com.suit.noteice.ui.theme.onPrimaryContainerDarkMediumContrast
import com.suit.noteice.ui.theme.onPrimaryContainerLight
import com.suit.noteice.ui.theme.onPrimaryContainerLightHighContrast
import com.suit.noteice.ui.theme.onPrimaryContainerLightMediumContrast
import com.suit.noteice.ui.theme.onPrimaryDark
import com.suit.noteice.ui.theme.onPrimaryDarkHighContrast
import com.suit.noteice.ui.theme.onPrimaryDarkMediumContrast
import com.suit.noteice.ui.theme.onPrimaryLight
import com.suit.noteice.ui.theme.onPrimaryLightHighContrast
import com.suit.noteice.ui.theme.onPrimaryLightMediumContrast
import com.suit.noteice.ui.theme.onSecondaryContainerDark
import com.suit.noteice.ui.theme.onSecondaryContainerDarkHighContrast
import com.suit.noteice.ui.theme.onSecondaryContainerDarkMediumContrast
import com.suit.noteice.ui.theme.onSecondaryContainerLight
import com.suit.noteice.ui.theme.onSecondaryContainerLightHighContrast
import com.suit.noteice.ui.theme.onSecondaryContainerLightMediumContrast
import com.suit.noteice.ui.theme.onSecondaryDark
import com.suit.noteice.ui.theme.onSecondaryDarkHighContrast
import com.suit.noteice.ui.theme.onSecondaryDarkMediumContrast
import com.suit.noteice.ui.theme.onSecondaryLight
import com.suit.noteice.ui.theme.onSecondaryLightHighContrast
import com.suit.noteice.ui.theme.onSecondaryLightMediumContrast
import com.suit.noteice.ui.theme.onSurfaceDark
import com.suit.noteice.ui.theme.onSurfaceDarkHighContrast
import com.suit.noteice.ui.theme.onSurfaceDarkMediumContrast
import com.suit.noteice.ui.theme.onSurfaceLight
import com.suit.noteice.ui.theme.onSurfaceLightHighContrast
import com.suit.noteice.ui.theme.onSurfaceLightMediumContrast
import com.suit.noteice.ui.theme.onSurfaceVariantDark
import com.suit.noteice.ui.theme.onSurfaceVariantDarkHighContrast
import com.suit.noteice.ui.theme.onSurfaceVariantDarkMediumContrast
import com.suit.noteice.ui.theme.onSurfaceVariantLight
import com.suit.noteice.ui.theme.onSurfaceVariantLightHighContrast
import com.suit.noteice.ui.theme.onSurfaceVariantLightMediumContrast
import com.suit.noteice.ui.theme.onTertiaryContainerDark
import com.suit.noteice.ui.theme.onTertiaryContainerDarkHighContrast
import com.suit.noteice.ui.theme.onTertiaryContainerDarkMediumContrast
import com.suit.noteice.ui.theme.onTertiaryContainerLight
import com.suit.noteice.ui.theme.onTertiaryContainerLightHighContrast
import com.suit.noteice.ui.theme.onTertiaryContainerLightMediumContrast
import com.suit.noteice.ui.theme.onTertiaryDark
import com.suit.noteice.ui.theme.onTertiaryDarkHighContrast
import com.suit.noteice.ui.theme.onTertiaryDarkMediumContrast
import com.suit.noteice.ui.theme.onTertiaryLight
import com.suit.noteice.ui.theme.onTertiaryLightHighContrast
import com.suit.noteice.ui.theme.onTertiaryLightMediumContrast
import com.suit.noteice.ui.theme.outlineDark
import com.suit.noteice.ui.theme.outlineDarkHighContrast
import com.suit.noteice.ui.theme.outlineDarkMediumContrast
import com.suit.noteice.ui.theme.outlineLight
import com.suit.noteice.ui.theme.outlineLightHighContrast
import com.suit.noteice.ui.theme.outlineLightMediumContrast
import com.suit.noteice.ui.theme.outlineVariantDark
import com.suit.noteice.ui.theme.outlineVariantDarkHighContrast
import com.suit.noteice.ui.theme.outlineVariantDarkMediumContrast
import com.suit.noteice.ui.theme.outlineVariantLight
import com.suit.noteice.ui.theme.outlineVariantLightHighContrast
import com.suit.noteice.ui.theme.outlineVariantLightMediumContrast
import com.suit.noteice.ui.theme.primaryContainerDark
import com.suit.noteice.ui.theme.primaryContainerDarkHighContrast
import com.suit.noteice.ui.theme.primaryContainerDarkMediumContrast
import com.suit.noteice.ui.theme.primaryContainerLight
import com.suit.noteice.ui.theme.primaryContainerLightHighContrast
import com.suit.noteice.ui.theme.primaryContainerLightMediumContrast
import com.suit.noteice.ui.theme.primaryDark
import com.suit.noteice.ui.theme.primaryDarkHighContrast
import com.suit.noteice.ui.theme.primaryDarkMediumContrast
import com.suit.noteice.ui.theme.primaryLight
import com.suit.noteice.ui.theme.primaryLightHighContrast
import com.suit.noteice.ui.theme.primaryLightMediumContrast
import com.suit.noteice.ui.theme.scrimDark
import com.suit.noteice.ui.theme.scrimDarkHighContrast
import com.suit.noteice.ui.theme.scrimDarkMediumContrast
import com.suit.noteice.ui.theme.scrimLight
import com.suit.noteice.ui.theme.scrimLightHighContrast
import com.suit.noteice.ui.theme.scrimLightMediumContrast
import com.suit.noteice.ui.theme.secondaryContainerDark
import com.suit.noteice.ui.theme.secondaryContainerDarkHighContrast
import com.suit.noteice.ui.theme.secondaryContainerDarkMediumContrast
import com.suit.noteice.ui.theme.secondaryContainerLight
import com.suit.noteice.ui.theme.secondaryContainerLightHighContrast
import com.suit.noteice.ui.theme.secondaryContainerLightMediumContrast
import com.suit.noteice.ui.theme.secondaryDark
import com.suit.noteice.ui.theme.secondaryDarkHighContrast
import com.suit.noteice.ui.theme.secondaryDarkMediumContrast
import com.suit.noteice.ui.theme.secondaryLight
import com.suit.noteice.ui.theme.secondaryLightHighContrast
import com.suit.noteice.ui.theme.secondaryLightMediumContrast
import com.suit.noteice.ui.theme.surfaceBrightDark
import com.suit.noteice.ui.theme.surfaceBrightDarkHighContrast
import com.suit.noteice.ui.theme.surfaceBrightDarkMediumContrast
import com.suit.noteice.ui.theme.surfaceBrightLight
import com.suit.noteice.ui.theme.surfaceBrightLightHighContrast
import com.suit.noteice.ui.theme.surfaceBrightLightMediumContrast
import com.suit.noteice.ui.theme.surfaceContainerDark
import com.suit.noteice.ui.theme.surfaceContainerDarkHighContrast
import com.suit.noteice.ui.theme.surfaceContainerDarkMediumContrast
import com.suit.noteice.ui.theme.surfaceContainerHighDark
import com.suit.noteice.ui.theme.surfaceContainerHighDarkHighContrast
import com.suit.noteice.ui.theme.surfaceContainerHighDarkMediumContrast
import com.suit.noteice.ui.theme.surfaceContainerHighLight
import com.suit.noteice.ui.theme.surfaceContainerHighLightHighContrast
import com.suit.noteice.ui.theme.surfaceContainerHighLightMediumContrast
import com.suit.noteice.ui.theme.surfaceContainerHighestDark
import com.suit.noteice.ui.theme.surfaceContainerHighestDarkHighContrast
import com.suit.noteice.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.suit.noteice.ui.theme.surfaceContainerHighestLight
import com.suit.noteice.ui.theme.surfaceContainerHighestLightHighContrast
import com.suit.noteice.ui.theme.surfaceContainerHighestLightMediumContrast
import com.suit.noteice.ui.theme.surfaceContainerLight
import com.suit.noteice.ui.theme.surfaceContainerLightHighContrast
import com.suit.noteice.ui.theme.surfaceContainerLightMediumContrast
import com.suit.noteice.ui.theme.surfaceContainerLowDark
import com.suit.noteice.ui.theme.surfaceContainerLowDarkHighContrast
import com.suit.noteice.ui.theme.surfaceContainerLowDarkMediumContrast
import com.suit.noteice.ui.theme.surfaceContainerLowLight
import com.suit.noteice.ui.theme.surfaceContainerLowLightHighContrast
import com.suit.noteice.ui.theme.surfaceContainerLowLightMediumContrast
import com.suit.noteice.ui.theme.surfaceContainerLowestDark
import com.suit.noteice.ui.theme.surfaceContainerLowestDarkHighContrast
import com.suit.noteice.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.suit.noteice.ui.theme.surfaceContainerLowestLight
import com.suit.noteice.ui.theme.surfaceContainerLowestLightHighContrast
import com.suit.noteice.ui.theme.surfaceContainerLowestLightMediumContrast
import com.suit.noteice.ui.theme.surfaceDark
import com.suit.noteice.ui.theme.surfaceDarkHighContrast
import com.suit.noteice.ui.theme.surfaceDarkMediumContrast
import com.suit.noteice.ui.theme.surfaceDimDark
import com.suit.noteice.ui.theme.surfaceDimDarkHighContrast
import com.suit.noteice.ui.theme.surfaceDimDarkMediumContrast
import com.suit.noteice.ui.theme.surfaceDimLight
import com.suit.noteice.ui.theme.surfaceDimLightHighContrast
import com.suit.noteice.ui.theme.surfaceDimLightMediumContrast
import com.suit.noteice.ui.theme.surfaceLight
import com.suit.noteice.ui.theme.surfaceLightHighContrast
import com.suit.noteice.ui.theme.surfaceLightMediumContrast
import com.suit.noteice.ui.theme.surfaceVariantDark
import com.suit.noteice.ui.theme.surfaceVariantDarkHighContrast
import com.suit.noteice.ui.theme.surfaceVariantDarkMediumContrast
import com.suit.noteice.ui.theme.surfaceVariantLight
import com.suit.noteice.ui.theme.surfaceVariantLightHighContrast
import com.suit.noteice.ui.theme.surfaceVariantLightMediumContrast
import com.suit.noteice.ui.theme.tertiaryContainerDark
import com.suit.noteice.ui.theme.tertiaryContainerDarkHighContrast
import com.suit.noteice.ui.theme.tertiaryContainerDarkMediumContrast
import com.suit.noteice.ui.theme.tertiaryContainerLight
import com.suit.noteice.ui.theme.tertiaryContainerLightHighContrast
import com.suit.noteice.ui.theme.tertiaryContainerLightMediumContrast
import com.suit.noteice.ui.theme.tertiaryDark
import com.suit.noteice.ui.theme.tertiaryDarkHighContrast
import com.suit.noteice.ui.theme.tertiaryDarkMediumContrast
import com.suit.noteice.ui.theme.tertiaryLight
import com.suit.noteice.ui.theme.tertiaryLightHighContrast
import com.suit.noteice.ui.theme.tertiaryLightMediumContrast
import noteice.composeapp.generated.resources.Res
import noteice.composeapp.generated.resources.funnel_display_regular
import noteice.composeapp.generated.resources.ubuntu_light
import noteice.composeapp.generated.resources.ubuntu_medium

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)



@Composable
fun NoteIceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
  val colorScheme = when {
      darkTheme -> darkScheme
      else -> lightScheme
  }
    val funnelDisplayFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.funnel_display_regular))
    val ubuntuLight = FontFamily(org.jetbrains.compose.resources.Font(Res.font.ubuntu_light))
    val ubuntuMedium = FontFamily(org.jetbrains.compose.resources.Font(Res.font.ubuntu_medium))

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography(
        titleLarge = TextStyle(
            fontFamily = funnelDisplayFamily,
            fontSize = 55.sp
        ),
        titleMedium = TextStyle(
            fontFamily = funnelDisplayFamily,
            fontSize = 25.sp
        ),
        labelMedium = TextStyle(
            fontFamily = ubuntuMedium,
            fontSize = 20.sp
        ),
        labelSmall = TextStyle(
            fontFamily = ubuntuLight,
            textAlign = TextAlign.Center,
            fontSize = 17.sp
        ),
        bodySmall = TextStyle(
            fontFamily = ubuntuMedium,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        ),
        bodyMedium = TextStyle(
            fontFamily = ubuntuMedium,
            fontSize = 21.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        )
    ),
    content = content
  )
}

