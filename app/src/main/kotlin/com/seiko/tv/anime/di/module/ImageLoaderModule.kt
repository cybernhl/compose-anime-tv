package com.seiko.tv.anime.di.module

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.memory.MemoryCache
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import org.koin.dsl.module
import timber.log.Timber
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
internal val imageLoaderModule = module {
    single {
        ImageLoader.Builder(get<PlatformContext>())
            .crossfade(true)
            .components {
                add(
                    OkHttpNetworkFetcherFactory(
                        callFactory = {
                            OkHttpClient()
                        }
                    )
                )
            }
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(androidContext(), 0.25)
                    .build()
            }
            .logger(DebugLogger())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val url = chain.request().url
                Timber.tag("OkHttp-Coil").d("Fetching image from: %s", url)
                chain.proceed(chain.request())
            }
            .build()
    }
}