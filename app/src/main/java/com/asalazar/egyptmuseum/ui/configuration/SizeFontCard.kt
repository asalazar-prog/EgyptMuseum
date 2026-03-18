package com.asalazar.egyptmuseum.ui.configuration

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asalazar.egyptmuseum.ui.theme.EgyptMuseumTheme

@Composable
fun SizeFontCard(
    currentValue: Float,
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onSurface
        )
    ) {
        HeaderCard(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

@Composable
private fun HeaderCard(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(Icons.Sharp.Settings, contentDescription = null)
        Text("Tamaño del Texto", style = MaterialTheme.typography.titleLarge)
    }
}

@Preview
@Composable
private fun SizeFontCardPreview() {
    EgyptMuseumTheme {
        SizeFontCard(0.1f, modifier = Modifier.fillMaxWidth()) { }
    }
}
