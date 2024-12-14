package org.db.wordle.backend.models

import androidx.annotation.Keep
import org.db.wordle.backend.models.KeyboardKeys.Key.Companion.englishKeys

abstract class KeyboardKeys(
  open val keys: List<Key>,
  ) {
  abstract fun withUpdatedButton(keys: List<Key>): KeyboardKeys
  data class Key(
    val button: Char,
    val equalityStatus: EqualityStatus?
  ) {
    @Keep
    companion object {
      val englishKeys = listOf(
        'Q',
        'W',
        'E',
        'R',
        'T',
        'Y',
        'U',
        'I',
        'O',
        'P',
        'A',
        'S',
        'D',
        'F',
        'G',
        'H',
        'J',
        'K',
        'L',
        'Z',
        'X',
        'C',
        'V',
        'B',
        'N',
        'M'
      )
    }
  }

  data class English(
    override val keys: List<Key> = englishKeys.map {
      Key(it, null)
    }.toList()
  ) : KeyboardKeys(keys) {
    override fun withUpdatedButton(keys: List<Key>): KeyboardKeys {
      return copy(keys = keys)
    }
  }
}
