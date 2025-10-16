package com.seiko.tv.anime.di.module

import coil.ImageLoader
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import timber.log.Timber
import okhttp3.OkHttpClient
internal val imageLoaderModule = module {
  single {
    ImageLoader.Builder(androidContext())
      .availableMemoryPercentage(0.25)
      .crossfade(true)
      .okHttpClient { get<OkHttpClient>() }
      .logger(object : coil.util.Logger {
        override var level: Int = android.util.Log.DEBUG

        override fun log(tag: String, priority: Int, message: String?, throwable: Throwable?) {
          // 将 Coil 的日志也重定向到 Timber
          if (priority >= level) {
            Timber.tag(tag).d(throwable, message)
          }
        }
      })
      .build()
  }

  single {
    OkHttpClient.Builder()
      // 可以添加 Interceptor 来更详细地监控网络请求
      .addInterceptor { chain ->
        val url = chain.request().url
        Timber.tag("OkHttp-Coil").d("Fetching image from: %s", url)
        chain.proceed(chain.request())
      }
      .build()
  }
}