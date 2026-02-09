package com.sarang.torang

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

@Composable
fun PhotoPickerTest(){
    var uris : List<Uri> by remember { mutableStateOf(listOf()) }
    var uri : Uri by remember { mutableStateOf("".toUri()) }
    var isActive : Boolean by remember { mutableStateOf(false) }
    val pickMultipleMedia =
        rememberLauncherForActivityResult(PickMultipleVisualMedia()) { it ->
            if (it.isNotEmpty()) {
                Log.d("__PhotoPicker", "Number of items selected: ${it.size}")
                uris = it
            }
            else { Log.d("__PhotoPicker", "No media selected") }
        }

    Column {
        Box(Modifier.height(300.dp)){
            ShortItem(short = ShortVideo(id = "0",
                      videoUrl = uri.toString()),
                      isActive = isActive)
        }

        Button({
            pickMultipleMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageAndVideo))
        }) {
            Text("PhotoPicker")
        }

        LazyColumn {
            items(items = uris){
                Button({
                    uri = it
                    isActive = false
                    isActive = true
                }) {
                    Text(text = it.toString())
                }
            }
        }

    }
}