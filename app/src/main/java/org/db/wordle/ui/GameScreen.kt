package org.db.wordle.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.db.wordle.backend.models.Level
import org.db.wordle.backend.viewmodel.GameViewModel

@Composable
fun GameScreen(
  level: Level,
  state: GameViewModel.State,
  onKey: (char: Char) -> Unit,
  onBackspace: () -> Unit,
  onSubmit: () -> Unit,
  shownError: () -> Unit,
  shownWon: () -> Unit,
  shownLost: () -> Unit
) {
  Box(
    Modifier
      .fillMaxSize()
      .padding(horizontal = 8.dp, vertical = 16.dp)
  ) {
    Column(Modifier.padding(bottom = 8.dp)) {

      GameHeader(level)

      GameGrid(
        state,
        modifier = Modifier
          .padding(top = 8.dp)
          .weight(1f)
          .fillMaxWidth(0.6f)
          .align(CenterHorizontally)
      )
      Spacer(modifier = Modifier.size(16.dp))
      GameKeyboard(
        state,
        onKey = onKey,
        onBackspace = onBackspace,
        onSubmit = onSubmit
      )
    }
    ErrorScreen(state, shownError)
    WonScreen(state, shownWon)
    GameOverScreen(state, shownLost)
  }
}