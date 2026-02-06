package com.sarang.torang

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private const val tag = "__ShortItem"

@Composable
fun ShortItem(
    short       : ShortVideo,
    isActive    : Boolean       = false,
    onPlayed    : () -> Unit    = {},
    onClick         : () -> Unit = {},
) {
    val active by rememberUpdatedState(isActive)
    var isPlayed by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        Log.d(tag, "${short.id}, ${short.videoUrl}")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        VideoPlayer(
            videoUrl = short.videoUrl,
            playWhenReady = active,
            onPlayed = { isPlayed = true
                         onPlayed() },
            onClick = onClick
        )

        if(!isPlayed){
            LocalThumbImageLoader.current.invoke(
                ThumbImageLoaderData(
                    modifier = Modifier.align(Alignment.Center),
                    url = short.thumbNailUrl
                )
            )
        }
    }
}

@Composable
fun ShortOverlay(
    likeCount: Int,
    isLiked: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            tint = if (isLiked) Color.Red else Color.White,
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = likeCount.toString(),
            color = Color.White
        )
    }
}