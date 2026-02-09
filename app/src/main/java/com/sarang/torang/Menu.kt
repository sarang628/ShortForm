package com.sarang.torang

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Menu(navController : NavHostController = rememberNavController()){
    Column {
        Button({
            navController.navigate("FeedRepository")
        }) {
            Text("FeedRepository")
        }

        Button({
            navController.navigate("Video")
        }) {
            Text("Video")
        }

        Button({
            navController.navigate("photoPicker")
        }) {
            Text("photoPicker")
        }

    }
}