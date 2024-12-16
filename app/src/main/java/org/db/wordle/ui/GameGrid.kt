package org.db.wordle.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.db.wordle.backend.models.EqualityStatus
import org.db.wordle.backend.models.WordStatus
import org.db.wordle.backend.viewmodel.GameViewModel



@Composable
internal fun GameGrid(
  state: GameViewModel.State,
  modifier: Modifier = Modifier
) {
  Box(modifier = modifier) {
    Column(modifier = Modifier.fillMaxWidth())
    {
      repeat(6) { row ->
        Row(
          Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
          horizontalArrangement = spacedBy(4.dp)
        )
        {
          repeat(5) { column ->
            val letter: Char?
            val status: EqualityStatus?

            if (row < state.game.guesses.size) {
              val guess = state.game.guesses[row]
              letter = guess.word.word[column]
              status = when (guess.wordStatus) {
                WordStatus.Correct -> EqualityStatus.Correct
                is WordStatus.Incorrect -> guess.wordStatus.equalityStatuses[column]
                WordStatus.NotExists -> EqualityStatus.Incorrect
              }
            } else {
              letter =
                if (row == state.game.guesses.size) state.currentlyEnteringWord?.getOrNull(
                  column
                ) else null
              status = null
            }


            WordLetterBox(
              letter = letter,
              status = status,
              modifier = Modifier.weight(1f)
            )
          }
        }
      }
    }
  }
}

@Composable
internal fun WordLetterBox(
  letter: Char?,
  status: EqualityStatus?,
  modifier: Modifier = Modifier
) {
  val color = when (status) {
    EqualityStatus.WrongPosition -> MaterialTheme.colorScheme.error
    EqualityStatus.Correct -> MaterialTheme.colorScheme.surface
    EqualityStatus.Incorrect -> MaterialTheme.colorScheme.onSurface
    null -> MaterialTheme.colorScheme.background
  }
  val textColor = when (status) {
    null -> MaterialTheme.colorScheme.onBackground
    else -> MaterialTheme.colorScheme.onPrimary
  }
  val borderModifier = if (status == null)
    Modifier.border(1.dp,MaterialTheme.colorScheme.onSurface)
  else Modifier
  BasicLetterBox(borderModifier, color, letter, textColor, modifier)
}

@Composable
internal fun BasicLetterBox(
  borderModifier: Modifier,
  color: Color,
  letter: Char?,
  textColor: Color,
  modifier: Modifier = Modifier
) {
  var lastChar by remember { mutableStateOf<Char?>(null) }
  if (letter != null) {
    lastChar = letter
  }
  Box(
    modifier
      .aspectRatio(1f)
      .clip(RoundedCornerShape(2.dp))
      .then(borderModifier)
      .background(animateColorAsState(targetValue = color.copy(alpha = 0.4f), label = "").value),
    contentAlignment = Alignment.Center) {
    AnimatedVisibility(letter != null) {
      Text(letter?.uppercase() ?: "",
        color = animateColorAsState(targetValue = textColor, label = "").value,
        style = TextStyle(
          fontSize = 20.sp,
          fontWeight = FontWeight.Black
        ))
    }
  }
}


@Preview
@Composable
internal fun CharacterBoxPreview() {
  Row {
    WordLetterBox(letter = 'A', status = null)
    WordLetterBox(letter = 'D', status = EqualityStatus.Incorrect)
    WordLetterBox(letter = 'I', status = EqualityStatus.WrongPosition)
    WordLetterBox(letter = 'B', status = EqualityStatus.Correct)
  }
}