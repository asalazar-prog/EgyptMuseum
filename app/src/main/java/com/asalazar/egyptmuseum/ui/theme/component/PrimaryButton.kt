package com.asalazar.egyptmuseum.ui.theme.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object PrimaryButtonDefault {
    val shape = RoundedCornerShape(4.dp)

    val MinHeight = 55.dp
}

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick,
        modifier = modifier.defaultMinSize(minHeight = PrimaryButtonDefault.MinHeight),
        content = content,
        shape = PrimaryButtonDefault.shape
    )
}
