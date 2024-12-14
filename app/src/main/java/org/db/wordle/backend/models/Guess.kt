package org.db.wordle.backend.models

data class Guess (
  val word: Word,
  val wordStatus: WordStatus
)
