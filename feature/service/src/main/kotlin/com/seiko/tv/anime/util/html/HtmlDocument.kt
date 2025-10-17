package com.seiko.tv.anime.util.html



interface HtmlDocument {
    fun select(cssQuery: String): HtmlElements
    fun body(): HtmlElement
    fun title(): String
}