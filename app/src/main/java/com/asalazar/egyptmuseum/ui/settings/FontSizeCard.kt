package com.asalazar.egyptmuseum.ui.settings

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asalazar.egyptmuseum.R
import com.asalazar.egyptmuseum.ui.theme.EgyptMuseumTheme
import com.asalazar.egyptmuseum.ui.theme.component.HeaderCard
import com.asalazar.egyptmuseum.ui.theme.component.MuseumCard

@Composable
fun FontSizeCard(
    currentValue: Float,
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit
) {
    MuseumCard(
        headerCard = { FontSizeHeaderCard() },
        modifier = modifier
    ) {
        FontSizeSelector(
            currentValue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = onValueChange
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FontSizeSelector(
    currentValue: Float,
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        FontSizeSelectorLabels(currentValue.toInt(), modifier = Modifier.fillMaxWidth())
        Slider(
            value = currentValue,
            onValueChange = onValueChange,
            valueRange = 0.5f..1.5f,
            steps = 1,
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(
                activeTickColor = Color.Transparent,
                inactiveTickColor = Color.Transparent
            ),
            thumb = {
                Box(
                    Modifier
                        .size(24.dp)
                        .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                        .border(
                            2.dp,
                            color = MaterialTheme.colorScheme.onSurface,
                            shape = CircleShape
                        )
                )
            }
        )
    }
}

@Composable
private fun FontSizeHeaderCard(modifier: Modifier = Modifier) {
    HeaderCard(
        title = "Tamaño del Texto",
        icon = Icons.Sharp.Settings,
        modifier = modifier
    )
}

@Composable
fun FontSizeSelectorLabels(
    currentValue: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        stringArrayResource(R.array.lbl_font_size_options).forEachIndexed { index, label ->
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = if (currentValue == index) MaterialTheme.colorScheme.tertiary
                else MaterialTheme.colorScheme.tertiary.copy(alpha = 0.6f),
                modifier = Modifier.weight(1f),
                textAlign = when (index) {
                    0 -> TextAlign.Start
                    1 -> TextAlign.Center
                    else -> TextAlign.End
                }
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun FontSizeCardPreview() {
    EgyptMuseumTheme {
        FontSizeCard(0.8f, modifier = Modifier.fillMaxWidth()) { }
    }
}
