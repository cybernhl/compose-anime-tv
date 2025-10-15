package com.seiko.compose.focuskit

import androidx.compose.foundation.clickable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent

fun Modifier.onPreviewFocusDirection(
  onPreviewFocusDirection: (FocusDirection) -> Boolean
) = this.onPreviewKeyEvent {
  getFocusDirection(it)?.run(onPreviewFocusDirection) ?: false
}

fun Modifier.onFocusDirection(
  onFocusDirection: (FocusDirection) -> Boolean
) = this.onKeyEvent {
  getFocusDirection(it)?.run(onFocusDirection) ?: false
}

fun Modifier.handleDirection(
  focusDirection: FocusDirection,
  onAction: () -> Boolean
) = this.onFocusDirection {
  if (it == focusDirection) onAction()
  else false
}

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.handleEnter(onAction: () -> Unit) =
  handleDirection(FocusDirection.Enter) { onAction(); true }

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.handleBack(onAction: () -> Unit) =
  handleDirection(FocusDirection.Exit) { onAction(); true }

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.handleEnterReturn(onAction: () -> Boolean) =
  handleDirection(FocusDirection.Enter, onAction)

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.handleBackReturn(onAction: () -> Boolean) =
  handleDirection(FocusDirection.Exit, onAction)

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.focusClick(onClick: () -> Unit) =
  clickable(onClick = onClick).handleEnter(onClick)
