package org.db.wordle.backend.repository

import org.db.wordle.backend.models.Word

interface WordRepository {
  val lastLevel: Long
  fun find(word: Word): Boolean
  fun random(): Word
  fun getWordForLevel(currentLevelNumber: Long): Word
}