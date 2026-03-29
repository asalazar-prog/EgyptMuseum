package com.asalazar.egyptmuseum.ui.theme.component

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

object PrimaryButtonDefault {
    val shape = RoundedCornerShape(4.dp)

    val MinHeight = 55.dp
}

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val haptic = LocalHapticFeedback.current

    Button(
        onClick = {
            haptic.performHapticFeedback(HapticFeedbackType.Confirm)
            onClick() },
        modifier = modifier.defaultMinSize(minHeight = PrimaryButtonDefault.MinHeight),
        content = {
            CompositionLocalProvider(
                LocalTextStyle provides MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                content = content
            )
        },
        shape = PrimaryButtonDefault.shape
    )
}
