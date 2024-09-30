package com.spx.hummingbird.modules.news.widgets.molecules

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView


@androidx.media3.common.util.UnstableApi
@Composable
fun ExoPlayerView(videoUrl: String) {
    val context = LocalContext.current
    val dataSourceFactory: DefaultHttpDataSource.Factory = remember {
        DefaultHttpDataSource.Factory().apply {
            setDefaultRequestProperties(
                mapOf(
                    "Referer" to "https://apis.fengniao.com",
                    "User-Agent" to "stagefright/1.2 (Linux;Android 12)"
                )
            )
        }
    }
    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .build()
    }
    val source = remember(videoUrl) {
        if (videoUrl.contains("m3u8"))
            getHlsMediaSource(videoUrl, dataSourceFactory)
        else
            getProgressiveMediaSource(videoUrl, dataSourceFactory)
    }
    LaunchedEffect(dataSourceFactory) {
        exoPlayer.setMediaSource(source)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
        exoPlayer.addListener(object : Player.Listener {
            override fun onEvents(player: Player, events: Player.Events) {

            }
        })
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
                controllerAutoShow = false
                hideController()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .then(
                Modifier.height(with(LocalContext.current.resources.displayMetrics) {
                    (widthPixels/density / (16f / 9f)).dp
                })
            )
    )
}

@androidx.media3.common.util.UnstableApi
private fun getHlsMediaSource(
    mediaUrl: String,
    dataSourceFactory: DataSource.Factory
): MediaSource {
    // Create a HLS media source pointing to a playlist uri.
    return HlsMediaSource.Factory(dataSourceFactory)
        .createMediaSource(MediaItem.fromUri(mediaUrl))
}

@androidx.media3.common.util.UnstableApi
private fun getProgressiveMediaSource(
    mediaUrl: String,
    dataSourceFactory: DataSource.Factory
): MediaSource {
    // Create a Regular media source pointing to a playlist uri.
    return ProgressiveMediaSource.Factory(dataSourceFactory)
        .createMediaSource(MediaItem.fromUri(Uri.parse(mediaUrl)))
}
