package com.sarang.torang

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ShortsPageScreen(
    viewModel: ShortsPlayListViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { viewModel.shorts.size }
    )

    VerticalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        ShortItem(
            short = viewModel.shorts[page],
            isActive = pagerState.currentPage == page
        )
    }
}