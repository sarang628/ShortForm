import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.sarang.torang.VideoPlayer

data class ShortVideo(
    val id: String,
    val videoUrl: String,
    val likeCount: Int,
    val isLiked: Boolean
)

class ShortsViewModel : ViewModel() {

    val shorts = mutableStateListOf(
        ShortVideo("1", "http://sarang628.iptime.org:89/video/1/output_part1.m3u8", 120, false),
        ShortVideo("2", "http://sarang628.iptime.org:89/video/2/output_part1.m3u8", 532, true),
        ShortVideo("3", "http://sarang628.iptime.org:89/video/3/output_part1.m3u8", 89, false),
        ShortVideo("4", "http://sarang628.iptime.org:89/video/4/output_part1.m3u8", 89, false),
        ShortVideo("5", "http://sarang628.iptime.org:89/video/5/output_part1.m3u8", 89, false),
        ShortVideo("6", "http://sarang628.iptime.org:89/video/6/output_part1.m3u8", 89, false),
        ShortVideo("7", "http://sarang628.iptime.org:89/video/7/output_part1.m3u8", 89, false),
        ShortVideo("8", "http://sarang628.iptime.org:89/video/8/output_part1.m3u8", 89, false),
        ShortVideo("9", "http://sarang628.iptime.org:89/video/9/output_part1.m3u8", 89, false),
        ShortVideo("10", "http://sarang628.iptime.org:89/video/10/output_part1.m3u8", 89, false),
        ShortVideo("11", "http://sarang628.iptime.org:89/video/11/output_part1.m3u8", 89, false),
        ShortVideo("12", "http://sarang628.iptime.org:89/video/12/output_part1.m3u8", 89, false),
        ShortVideo("13", "http://sarang628.iptime.org:89/video/13/output_part1.m3u8", 89, false),
    )
}

@Composable
fun ShortsScreen(
    viewModel: ShortsViewModel = ShortsViewModel()
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

@Composable
fun ShortItem(
    short: ShortVideo,
    isActive: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        VideoPlayer(
            videoUrl = short.videoUrl,
            playWhenReady = isActive
        )

        ShortOverlay(
            likeCount = short.likeCount,
            isLiked = short.isLiked,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
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