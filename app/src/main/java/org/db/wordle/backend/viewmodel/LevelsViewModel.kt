package org.db.wordle.backend.viewmodel

import org.db.wordle.backend.models.Level
import org.db.wordle.backend.repository.LevelRepository
import org.db.wordle.backend.usecase.GetNextLevel
import org.db.wordle.backend.usecase.ResetLevels

class LevelsViewModel(
  private val levelRepository: LevelRepository,
  private val getNextLevel: GetNextLevel,
  private val resetLevels: ResetLevels,
) : BaseViewModel<LevelsViewModel.State>(State()){
  data class State(
    val currentLevel: Level? = null,
    val lastLevelReached: Boolean = false,
  )

  init {
    updateLevel()
  }

  fun levelPassed() {
    currentState().currentLevel?.let { levelRepository.levelPassed(it) }
    updateLevel()
  }

  private fun updateLevel() {
    val nextLevel = getNextLevel.execute()
    if (nextLevel == null) {
      updateState { copy(lastLevelReached = true, currentLevel = null) }
      return
    }
    updateState {
      copy(currentLevel = nextLevel, lastLevelReached = false)
    }
  }
  fun reset() {
    resetLevels.execute()
    updateLevel()
  }
}
