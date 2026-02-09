package com.sarang.torang

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sarang.torang.repository.feed.FeedFlowRepository
import com.sarang.torang.repository.feed.FeedLoadRepository
import com.sarang.torang.repository.feed.FeedRepository
import com.sarang.torang.repository.test.feed.FeedRepositoryTestScreen
import com.sryang.torang.ui.TorangTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.core.net.toUri

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
            val scaffold = Scaffold(modifier = Modifier.fillMaxSize()) {
                NavHost(modifier            = Modifier.padding(it),
                    navController       = navController,
                    startDestination    = "Menu"){
                    composable("Menu"){
                        Menu(navController = navController)
                    }
                    composable("FeedRepository"){
                        FeedRepositoryTestScreen(
                            feedRepository = feedRepository,
                            feedFlowRepository = feedFlowRepository,
                            feedLoadRepository = feedLoadRepository
                        )
                    }

                    composable("Video"){
                        VideoTest(feedLoadRepository)
                    }

                    composable("photoPicker") {
                        PhotoPickerTest()
                    }
                }
            }

                LaunchedEffect(Unit) {
                    feedLoadRepository.setLoadTrigger(true)
                }
                scaffold
            }
        }
    }
}
