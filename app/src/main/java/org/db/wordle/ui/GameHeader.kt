package org.db.wordle.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.db.wordle.backend.models.Level
import org.db.wordle.R

@Composable
internal fun ColumnScope.GameHeader(level: Level, modifier: Modifier = Modifier) {
  var revealing by remember(level) { mutableStateOf(false) }
  GameHeader(modifier) {
    LevelHeaderContent(level, revealing) {
      revealing = it
    }
  }
}

@Composable
internal fun ColumnScope.GameHeader(
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit,
) {

  Column(
    modifier
      .align(Alignment.CenterHorizontally)
  ) {

    Text(
      text = stringResource(id = R.string.app_name),
      style = MaterialTheme.typography.headlineLarge,
      fontWeight = FontWeight.Black,
      fontFamily = FontFamily.Serif,
      modifier = Modifier.align(
        Alignment.CenterHorizontally
      )
    )
    content()
  }
}

@Composable
fun ColumnScope.LevelHeaderContent(
  level: Level,
  revealing: Boolean,
  onRevealChanged: (Boolean) -> Unit
) {
  Row(
    modifier = Modifier.Companion
      .align(
        Alignment.CenterHorizontally
      )
      .padding(top = 16.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = "Level ${level.number}",
      style = MaterialTheme.typography.headlineSmall,
      fontWeight = FontWeight.Black,
      fontFamily = FontFamily.Serif,
    )
    Box(modifier = Modifier.padding(start = 16.dp)) {
      if (!revealing) {
        Text(text = "(reveal)",
          style = MaterialTheme.typography.labelSmall,
          modifier = Modifier.clickable {
            onRevealChanged(true)
          })
      } else {
        Text(text = level.word.word, style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.primary,

          modifier = Modifier.clickable {
            onRevealChanged(false)
          }
        )
      }
    }
  }
}
