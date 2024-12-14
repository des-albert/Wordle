package org.db.wordle.backend.usecase

import org.db.wordle.backend.models.Level
import org.db.wordle.backend.repository.LevelRepository
import org.db.wordle.backend.repository.WordRepository


class GetNextLevel(
  private val wordRepository: WordRepository,
  private val levelRepository: LevelRepository,
) {
  fun execute(): Level? {
    val currentLevelNumber = levelRepository.getCurrentLevelNumber()

    if (currentLevelNumber >= wordRepository.lastLevel + 1) return null

    return Level(currentLevelNumber, wordRepository.getWordForLevel(currentLevelNumber))
  }
}