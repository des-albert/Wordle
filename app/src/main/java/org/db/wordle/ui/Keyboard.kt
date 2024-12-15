package org.db.wordle.ui

import android.media.MediaPlayer
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.db.wordle.backend.models.EqualityStatus
import org.db.wordle.backend.models.KeyboardKeys.Key
import org.db.wordle.backend.viewmodel.GameViewModel
import org.db.wordle.R

@Composable
internal fun GameKeyboard(
  state: GameViewModel.State,
  modifier: Modifier = Modifier,
  onKey: (char: Char) -> Unit,
  onBackspace: () -> Unit,
  onSubmit: () -> Unit
) {
  Box(modifier) {
    Column {
      Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        repeat(10) {
          val key = state.game.availableKeyboard.keys[it]
          KeyboardKey(key, onKey, Modifier.weight(1f))
        }
      }

      Spacer(Modifier.size(4.dp))
      Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(start = 8.dp)
      ) {
        repeat(9) {
          val key = state.game.availableKeyboard.keys[10 + it]
          KeyboardKey(key, onKey, Modifier.weight(1f))
        }
      }
      Spacer(Modifier.size(4.dp))

      Row(horizontalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier) {
        repeat(7) {
          val key = state.game.availableKeyboard.keys[19 + it]
          KeyboardKey(key, onKey, Modifier.weight(1f))
        }
        KeyboardKey(
          text = "⌫",
          modifier = Modifier.width(40.dp),
          onClick = onBackspace
        )
      }
      Spacer(Modifier.size(6.dp))
      Box(
        modifier
          .align(CenterHorizontally)
          .height(40.dp)
          .clip(RoundedCornerShape(2.dp))
          .background(MaterialTheme.colorScheme.primary)
          .clickable(onClick = onSubmit), Alignment.Center
      ) {
        Text(
          text = "CHECK",
          fontSize = 12.sp,
          fontWeight = FontWeight.Black,

          modifier = Modifier
            .padding(horizontal = 4.dp)
            .clip(RoundedCornerShape(4.dp))
        )
      }

    }
  }
}

@Composable
private fun KeyboardKey(
  key: Key,
  onKey: (char: Char) -> Unit,
  modifier: Modifier = Modifier
) {
  KeyboardKey(
    key.button.toString().uppercase(),
    modifier = modifier,
    key.equalityStatus
  ) {
    onKey(key.button)
  }
}

@Composable
private fun KeyboardKey(
  text: String,
  modifier: Modifier = Modifier,
  status: EqualityStatus? = null,
  onClick: () -> Unit
) {
  val context = LocalContext.current
  val mediaPlayer = remember { MediaPlayer.create(context, R.raw.click) }
  val color by animateColorAsState(
    targetValue = when (status) {
      EqualityStatus.Incorrect -> MaterialTheme.colorScheme.onTertiary
      else -> MaterialTheme.colorScheme.onSecondary
    }, label = "keyboardBackground"
  )
  val testColor by animateColorAsState(
    targetValue = when (status) {
      EqualityStatus.WrongPosition -> MaterialTheme.colorScheme.error
      EqualityStatus.Correct -> MaterialTheme.colorScheme.surface
      EqualityStatus.Incorrect, null -> MaterialTheme.colorScheme.secondaryContainer
    }, label = "keyboard"
  )
  Box(
    modifier
      .height(40.dp)
      .background(color)
      .clip(RoundedCornerShape(2.dp))
      .clickable(onClick = {
        mediaPlayer.start()
        onClick()
      })
    ,
    Alignment.Center
  ) {
    Text(
      modifier = Modifier,
      text = text,
      color = testColor,
      fontSize = 24.sp
    )
  }

}