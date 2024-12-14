package org.db.wordle.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import org.db.wordle.backend.models.Game
import org.db.wordle.backend.models.Level
import org.db.wordle.backend.usecase.GetWordStatus
import org.db.wordle.backend.viewmodel.GameViewModel

@Composable
internal fun WordScreen(
  level: Level,
  getWordStatus: GetWordStatus,
  levelCompleted: () -> Unit
) {
  val word = level.word
  val viewModel = remember(word) {
    val initialGame = Game(word, listOf(), 5)
    GameViewModel(initialGame, getWordStatus)
  }
  val state by viewModel.state().collectAsState()
  GameScreen(
    level,
    state,
    onKey = {
    viewModel.characterEntered(it)
  },
    onBackspace = {
      viewModel.backspacePressed()
    },
    onSubmit = {
      viewModel.submit()
    },
    shownError = {
      viewModel.shownNotExists()
    },
    shownWon = levelCompleted,

    shownLost = {
      viewModel.shownLost()
    }
  )
}