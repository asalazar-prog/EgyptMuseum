package com.asalazar.egyptmuseum.ui.theme.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.media3.session.MediaController
import com.asalazar.egyptmuseum.R
import com.asalazar.egyptmuseum.ui.util.vibrateWrapperConfirm
import kotlinx.coroutines.delay

@Composable
fun EgyptAudioController(
    controller: MediaController?,
    modifier: Modifier = Modifier
) {
    var currentPosition by remember { mutableLongStateOf(0L) }
    var totalDuration by remember { mutableLongStateOf(0L) }

    var isDragging by remember { mutableStateOf(false) }
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    var isPlaying by remember { mutableStateOf(false) }

    LaunchedEffect(controller, isDragging) {
        if (controller == null) return@LaunchedEffect
        if (isDragging) {
            controller.pause()
            isPlaying = false
        }
        while (!isDragging) {
            currentPosition = controller.currentPosition.coerceAtLeast(0L)
            totalDuration = controller.duration.coerceAtLeast(0L)
            isPlaying = controller.isPlaying
            delay(500)
        }
    }

    Surface(shape = RoundedCornerShape(10.dp), modifier = modifier) {
        Column(Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                stringResource(R.string.title_audio_controller),
                style = MaterialTheme.typography.titleLarge
            )

            SliderAudioController(
                isDragging,
                sliderPosition,
                currentPosition,
                totalDuration,
                onValueChange = { newValue ->
                    isDragging = true
                    sliderPosition = newValue
                },
                onValueChangeFinished = {
                    controller?.seekTo(sliderPosition.toLong())
                    isDragging = false
                },
                modifier = Modifier.fillMaxWidth()
            )

            ButtonsAudioController(
                isPlaying = isPlaying,
                onRefresh = vibrateWrapperConfirm { controller?.seekTo(0) },
                onPause = vibrateWrapperConfirm { controller?.pause() },
                onPlay = vibrateWrapperConfirm { controller?.play() },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun SliderAudioController(
    isDragging: Boolean,
    sliderPosition: Float,
    currentPosition: Long,
    totalDuration: Long,
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: () -> Unit
) {
    Column(modifier = modifier) {
        Slider(
            value = if (isDragging) sliderPosition else currentPosition.toFloat(),
            valueRange = 0f..(if (totalDuration > 0) totalDuration.toFloat() else 1f),
            onValueChange = onValueChange,
            onValueChangeFinished = onValueChangeFinished,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatTime(currentPosition),
                style = MaterialTheme.typography.labelSmall
            )
            Text(text = formatTime(totalDuration), style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
private fun ButtonsAudioController(
    isPlaying: Boolean,
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit,
    onPause: () -> Unit,
    onPlay: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {

        IconButton(onClick = onRefresh) {
            Icon(Icons.Default.Refresh, contentDescription = stringResource(R.string.lbl_restart))
        }

        FloatingActionButton(
            onClick = {
                if (isPlaying) onPause() else onPlay()
            },
            shape = CircleShape,
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) stringResource(R.string.lbl_pause)
                else stringResource(R.string.lbl_play)
            )
        }

        Box(Modifier.size(24.dp))
    }
}

private fun formatTime(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}
