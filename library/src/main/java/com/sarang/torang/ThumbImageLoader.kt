package com.sarang.torang

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

data class ThumbImageLoaderData(
    val modifier : Modifier,
    val url : String
)

typealias ThumbImageLoader = @Composable (ThumbImageLoaderData)-> Unit

val LocalThumbImageLoader = compositionLocalOf<ThumbImageLoader> {
    {
        Box(modifier = it.modifier){
            Text(text  = it.url,
                 color = Color.White)
        }
    }
}