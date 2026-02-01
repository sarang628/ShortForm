package com.sarang.torang

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

@Composable
fun ShortListScreen(
    viewModel: ShortsPlayListViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    ShortListScreen(viewModel.shorts)
}
@Composable
fun ShortListScreen(
    videos : List<ShortVideo>
) {

    val listState = rememberLazyListState()

    val playingId = remember { mutableStateOf<String?>(null) }

    val playingIndex = remember { mutableStateOf(0) }

    LaunchedEffect(listState) {
        snapshotFlow {
            listState.isScrollInProgress to listState.layoutInfo.visibleItemsInfo
        }
            .filter { (scrolling, _) -> !scrolling }
            .map { (_, visibleItems) -> visibleItems }
            .collect { visibleItems ->
                if (visibleItems.isEmpty()) return@collect

                val layoutInfo = listState.layoutInfo

                val index = visibleItems.maxByOrNull { item ->
                    minOf(item.offset + item.size, layoutInfo.viewportEndOffset) -
                            maxOf(item.offset, layoutInfo.viewportStartOffset)
                }?.index ?: return@collect

                playingIndex.value = index

                playingId.value = videos.getOrNull(index)?.id
            }
    }

    LazyColumn(state = listState,) {
        items(items = videos,
              key   = { it.id },
              contentType = { "short_video" }) {
            Box(Modifier.height(400.dp).fillMaxWidth()){
                var count by rememberSaveable { mutableStateOf(0) }
                ShortItem(
                    short = it,
                    isActive = it.id == playingId.value,
                    onPlayed = {count++}
                )
            }
        }
    }
}