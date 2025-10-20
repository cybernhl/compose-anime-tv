package com.seiko.tv.anime.data.service

import com.seiko.tv.anime.data.remote.response.sakura.DetailResponse
import com.seiko.tv.anime.data.remote.response.sakura.HomeResponse
import com.seiko.tv.anime.data.remote.response.sakura.TagResponse
import com.seiko.tv.anime.data.remote.response.sakura.TimelineResponse
import com.seiko.tv.anime.data.remote.response.sakura.VideoResponse
import com.seiko.tv.anime.extractDetailResponse
import com.seiko.tv.anime.extractHomeResponse
import com.seiko.tv.anime.extractTagResponse
import com.seiko.tv.anime.extractTimeLineResponse
import com.seiko.tv.anime.extractVideoResponse
import com.seiko.tv.anime.util.html.HtmlParser
import com.seiko.tv.anime.util.parser.JsoupHtmlParser
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
private const val USER_AGENT =
  "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36 Edg/91.0.864.59"

class SakuraService(private val baseUrl: String, private val client: HttpClient, ) {
//  val htmlParser: HtmlParser = KsoupHtmlParser()
private val htmlParser: HtmlParser = JsoupHtmlParser()
  internal fun wrapUrl(href: String): String {
    return baseUrl + href
  }
  internal suspend fun getHomeResponse(url: String = wrapUrl("/")): HomeResponse {
    val html = getHtml(url)
//     return Hson.deserializeKData(html)
    val doc = htmlParser.parse(html, url)
    return extractHomeResponse(doc)
  }
  internal suspend fun getDetailResponse(url: String): DetailResponse {
    val html = getHtml(url)
//    return Hson.deserializeKData(html)
    val doc = htmlParser.parse(html, url)
    return extractDetailResponse(doc)
  }
  internal suspend fun getVideoResponse(url: String): VideoResponse {
    val html = getHtml(url)
//    return Hson.deserializeKData(html)
    val doc = htmlParser.parse(html, url)
    return extractVideoResponse(doc)
  }
  internal suspend fun getTimeLineResponse(): TimelineResponse {
    val url = wrapUrl("/")
    val html = getHtml(url)
//    return Hson.deserializeKData(html)
    val doc = htmlParser.parse(html, url)
    return extractTimeLineResponse(doc)
  }
  internal suspend fun getTagResponse(url: String): TagResponse {
    val html = getHtml(url)
//    return Hson.deserializeKData(html)
    val doc = htmlParser.parse(html, url)
    return extractTagResponse(doc)
  }
  private suspend fun getHtml(url: String): String {
    return client.get {
      url(url)
      header("User-Agent", USER_AGENT)
    }.bodyAsText()
  }
}
