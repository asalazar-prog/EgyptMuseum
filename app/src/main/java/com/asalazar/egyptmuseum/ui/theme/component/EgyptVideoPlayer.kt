package com.asalazar.egyptmuseum.ui.theme.component

import android.util.Log
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.SeekParameters
import androidx.media3.ui.PlayerView

@OptIn(UnstableApi::class)
@Composable
fun EgyptVideoPlayer(
    videoResId: Int,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .setSeekBackIncrementMs(10000)
            .setSeekForwardIncrementMs(10000)
            .build().apply {
                playWhenReady = false
                setSeekParameters(SeekParameters.CLOSEST_SYNC)
                val uri = "android.resource://${context.packageName}/$videoResId".toUri()
                setMediaItem(MediaItem.fromUri(uri))
                prepare()
            }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> exoPlayer.pause()
                Lifecycle.Event.ON_RESUME -> exoPlayer.playWhenReady = true
                Lifecycle.Event.ON_DESTROY -> exoPlayer.release()
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            exoPlayer.release()
        }
    }

    Box(modifier = modifier.background(Color.Black)) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    useController = false
                }
            },
            modifier = Modifier.fillMaxSize()
        )
        VideoControlOverlay(exoPlayer)
    }
}

@Composable
private fun VideoControlOverlay(player: Player) {
    var playWhenReady by remember { mutableStateOf(player.playWhenReady) }
    var playbackState by remember { mutableIntStateOf(player.playbackState) }


    DisposableEffect(player) {
        val listener = object : Player.Listener {
            override fun onEvents(player: Player, events: Player.Events) {
                if (events.containsAny(
                        Player.EVENT_PLAY_WHEN_READY_CHANGED,
                        Player.EVENT_PLAYBACK_STATE_CHANGED
                    )
                ) {
                    playWhenReady = player.playWhenReady
                    playbackState = player.playbackState
                }
            }
        }
        player.addListener(listener)
        onDispose { player.removeListener(listener) }
    }

    val shouldShowPause = playWhenReady && playbackState != Player.STATE_ENDED

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { player.seekBack() }) {
            Icon(Icons.Default.Replay10, contentDescription = "Atrasar 10s", tint = Color.White)
        }

        Spacer(modifier = Modifier.width(24.dp))

        FloatingActionButton(
            onClick = {
                if (playbackState == Player.STATE_ENDED) {
                    player.seekTo(0)
                    player.play()
                } else if (playWhenReady) {
                    player.pause()
                } else {
                    player.play()
                }
            },
            containerColor = Color.White.copy(alpha = 0.5f),
            shape = CircleShape
        ) {
            Icon(
                imageVector = if (shouldShowPause) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.width(24.dp))

        IconButton(onClick = { player.seekForward() }) {
            Icon(Icons.Default.Forward10, contentDescription = "Adelantar 10s", tint = Color.White)
        }
    }
}
