package org.db.wordle.backend.repository

import org.db.wordle.backend.models.Level

interface LevelRepository {
  fun getCurrentLevelNumber(): Long
  fun levelPassed(level: Level)
  fun reset()
}