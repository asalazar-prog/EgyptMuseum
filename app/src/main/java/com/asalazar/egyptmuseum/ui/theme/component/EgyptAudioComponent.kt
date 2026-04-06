package com.asalazar.egyptmuseum.ui.theme.component

import android.content.ComponentName
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.asalazar.egyptmuseum.service.EgyptPlaybackService
import com.google.common.util.concurrent.MoreExecutors

@Composable
fun EgyptAudioComponent(audioUri: Uri) {
    val context = LocalContext.current
    val sessionToken = remember {
        SessionToken(context, ComponentName(context, EgyptPlaybackService::class.java))
    }

    val controllerFuture = remember {
        MediaController.Builder(context, sessionToken).buildAsync()
    }

    var playerController by remember { mutableStateOf<MediaController?>(null) }

    DisposableEffect(controllerFuture) {
        controllerFuture.addListener({
            playerController = controllerFuture.get()
        }, MoreExecutors.directExecutor())

        onDispose {
            MediaController.releaseFuture(controllerFuture)
        }
    }

    Column {
        Button(onClick = {
            playerController?.setMediaItem(MediaItem.fromUri(audioUri))
            playerController?.prepare()
            playerController?.play()
        }) {
            Text("Iniciar Guía de Audio")
        }
    }
}
