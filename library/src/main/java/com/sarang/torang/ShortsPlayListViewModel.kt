package com.sarang.torang

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class ShortsPlayListViewModel : ViewModel() {
    val shorts = mutableStateListOf(
        ShortVideo("793", "http://sarang628.iptime.org:89/review_images/1/300/2026-01-31/11_19_28_107.m3u8"),
        ShortVideo("779", "http://sarang628.iptime.org:89/review_images/1/239/2026-01-29/07_39_51_648.m3u8"),
        ShortVideo("780", "http://sarang628.iptime.org:89/review_images/1/286/2026-01-29/07_40_34_170.m3u8"),
        ShortVideo("781", "http://sarang628.iptime.org:89/review_images/1/297/2026-01-29/07_41_05_441.m3u8"),
        ShortVideo("782", "http://sarang628.iptime.org:89/review_images/1/296/2026-01-29/07_41_29_914.m3u8"),
        ShortVideo("783", "http://sarang628.iptime.org:89/review_images/1/281/2026-01-29/07_42_07_258.m3u8"),
        ShortVideo("784", "http://sarang628.iptime.org:89/review_images/1/291/2026-01-29/07_42_42_840.m3u8"),
        ShortVideo("785", "http://sarang628.iptime.org:89/review_images/1/284/2026-01-29/07_43_18_068.m3u8"),
        ShortVideo("786", "http://sarang628.iptime.org:89/review_images/1/275/2026-01-29/07_43_54_394.m3u8"),
        ShortVideo("787", "http://sarang628.iptime.org:89/review_images/1/260/2026-01-29/07_44_26_453.m3u8"),
        ShortVideo("788", "http://sarang628.iptime.org:89/review_images/1/326/2026-01-29/07_45_04_721.m3u8"),
        ShortVideo("789", "http://sarang628.iptime.org:89/review_images/1/299/2026-01-29/07_45_38_813.m3u8"),
    )
}
