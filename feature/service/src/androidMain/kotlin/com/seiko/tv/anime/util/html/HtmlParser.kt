package com.seiko.tv.anime.util.html

interface HtmlParser {
    fun parse(html: String): HtmlDocument
    fun parse(html: String, baseUri: String): HtmlDocument
}