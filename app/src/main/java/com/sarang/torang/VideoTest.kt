package com.sarang.torang

import TorangAsyncImage
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.sarang.torang.repository.feed.FeedLoadRepository
import kotlinx.coroutines.flow.stateIn

@Composable
fun VideoTest(feedLoadRepository : FeedLoadRepository){
    val coroutine = rememberCoroutineScope()
    val videos = remember { mutableStateListOf<ShortVideo>() }
    LaunchedEffect(Unit) {
        feedLoadRepository.feeds
            .stateIn(coroutine)
            .collect { feeds ->
                videos.clear()
                feeds?.forEach {
                    videos.add(
                        ShortVideo(
                            id = it.review.reviewId.toString(),
                            videoUrl = BuildConfig.REVIEW_IMAGE_SERVER_URL + it.images[0].pictureUrl,
                            thumbNailUrl =
                                BuildConfig.REVIEW_IMAGE_SERVER_URL +
                                        it.images[0].pictureUrl?.substringBeforeLast(".") + ".jpg"
                        )
                    )
                }
            }
    }
    Box {
        CompositionLocalProvider(
            LocalThumbImageLoader provides {
                TorangAsyncImage(
                    modifier = it.modifier,
                    model = it.url
                )
            }
        ) {
            //ShortsPageScreen()
            ShortListScreen(videos)
//                                    ShortListScreen()
        }
    }
}