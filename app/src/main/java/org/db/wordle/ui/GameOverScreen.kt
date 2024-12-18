package org.db.wordle.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.db.wordle.backend.viewmodel.GameViewModel

@Composable
internal fun BoxScope.GameOverScreen(
  state: GameViewModel.State,
  shownLost: () -> Unit,
) {

  AnimatedVisibility(
    state.game.isOver, modifier = Modifier.fillMaxSize(),
    enter = fadeIn(), exit = fadeOut()
  ) {

    Box(Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.onBackground)
      .clickable {
        shownLost()
      }
      .padding(16.dp)
      .wrapContentHeight()
      .clip(RoundedCornerShape(5.dp))
      .background(MaterialTheme.colorScheme.error),
      Alignment.Center) {
      Text(
        text = "You lost. Tap to retry.",
        color = MaterialTheme.colorScheme.onError,
        modifier = Modifier.padding(32.dp),
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Black
      )
    }
  }
}
