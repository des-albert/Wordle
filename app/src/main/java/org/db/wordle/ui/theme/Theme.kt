package org.db.wordle.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme

import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable




private val LightColors = darkColorScheme(
  primary = primaryLight,
  secondary = secondaryLight,
  tertiary = tertiaryLight,
  background = backgroundLight,
  onBackground = onBackgroundLight,
  onPrimary = onPrimaryLight,
  surface = correctBackground,
  error = wrongPositionBackground,
  onSurface = incorrectBackground,
  onSecondary = keyboard,
  onTertiary = keyboardDisabled,
  secondaryContainer = onKeyboard,
  primaryContainer = wonScreen

)
private val DarkColors = lightColorScheme(
  primary = primaryDark,
  secondary = secondaryDark,
  tertiary = tertiaryDark,
  background = backgroundDark,
  onBackground = onBackgroundDark,
  onPrimary = onPrimaryDark,
  surface = correctBackground,
  error = wrongPositionBackground,
  onSurface = incorrectBackground,
  onSecondary = keyboard,
  onTertiary = keyboardDisabled,
  secondaryContainer = onKeyboard,
  primaryContainer = wonScreen


  /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)


@Composable
fun WordleTheme(
  useDarkTheme: Boolean = isSystemInDarkTheme(),

  content: @Composable () -> Unit
) {
  val colors = if (!useDarkTheme) {
    LightColors
  } else {
    DarkColors
  }


  MaterialTheme(
    colorScheme = colors,
    typography = Typography,
    content = content
  )
}