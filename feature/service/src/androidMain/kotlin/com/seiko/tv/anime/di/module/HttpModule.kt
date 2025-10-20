package com.seiko.tv.anime.di.module

import com.seiko.tv.anime.YHDM_BAS_URL
import com.seiko.tv.anime.data.service.SakuraService
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import org.koin.dsl.module
import timber.log.Timber
internal val httpModule = module {
    single {
        val client = createHttpClient()
        SakuraService(YHDM_BAS_URL, client)
    }
}

private fun createHttpClient(): HttpClient {
    return HttpClient(CIO) {
        install(HttpTimeout) {
            requestTimeoutMillis = 15000L
            connectTimeoutMillis = 15000L
            socketTimeoutMillis = 15000L
        }
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Timber.e(message)
                }
            }
        }
    }
}