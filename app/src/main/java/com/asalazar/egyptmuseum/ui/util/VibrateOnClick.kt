package com.asalazar.egyptmuseum.ui.util

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback

fun Modifier.vibrateOnClick(onClick: () -> Unit): Modifier = composed {
    val haptic = LocalHapticFeedback.current
    this.clickable {
        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        onClick()
    }
}
