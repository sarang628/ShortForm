package com.sarang.torang

import TorangAsyncImage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sarang.torang.repository.feed.FeedFlowRepository
import com.sarang.torang.repository.feed.FeedLoadRepository
import com.sarang.torang.repository.feed.FeedRepository
import com.sarang.torang.repository.test.feed.FeedRepositoryTestScreen
import com.sryang.torang.ui.TorangTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var feedRepository: FeedRepository
    @Inject lateinit var feedLoadRepository: FeedLoadRepository
    @Inject lateinit var feedFlowRepository: FeedFlowRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            TorangTheme {
                LaunchedEffect(Unit) {
                    feedLoadRepository.setLoadTrigger(true)
                }
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavHost(modifier            = Modifier.padding(it),
                            navController       = navController,
                            startDestination    = "Menu"){
                        composable("Menu"){
                            Column {
                                Button({
                                    navController.navigate("FeedRepository")
                                }) {
                                    Text("Menu")
                                }

                                Button({
                                    navController.navigate("Video")
                                }) {
                                    Text("Video")
                                }
                            }
                        }
                        composable("FeedRepository"){
                            FeedRepositoryTestScreen(
                                feedRepository = feedRepository,
                                feedFlowRepository = feedFlowRepository,
                                feedLoadRepository = feedLoadRepository
                            )
                        }

                        composable("Video"){
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
                                        TorangAsyncImage(model = it.url)
                                    }
                                ) {
                                    //ShortsPageScreen()
                                    ShortListScreen(videos)
//                                    ShortListScreen()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
