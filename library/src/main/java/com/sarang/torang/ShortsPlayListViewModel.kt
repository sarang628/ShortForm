package com.sarang.torang

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ShortsPlayListViewModel : ViewModel() {

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