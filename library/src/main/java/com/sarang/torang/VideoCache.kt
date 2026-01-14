package com.sarang.torang

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import java.io.File
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.cache.CacheDataSource

@UnstableApi
object VideoCache {

    private var cache: SimpleCache? = null

    fun getInstance(context: Context): SimpleCache {
        if (cache == null) {
            val cacheDir = File(context.cacheDir, "video_cache")
            val evictor = LeastRecentlyUsedCacheEvictor(500L * 1024 * 1024) // 500MB
            val databaseProvider = StandaloneDatabaseProvider(context)

            cache = SimpleCache(cacheDir, evictor, databaseProvider)
        }
        return cache!!
    }
}


@OptIn(UnstableApi::class)
fun cacheDataSourceFactory(context: Context): CacheDataSource.Factory {
    return CacheDataSource.Factory()
        .setCache(VideoCache.getInstance(context))
        .setUpstreamDataSourceFactory(DefaultDataSource.Factory(context))
        .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
}