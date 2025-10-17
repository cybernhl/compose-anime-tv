package com.seiko.tv.anime.util.html

interface HtmlElement {
    fun select(cssQuery: String): HtmlElements
    fun tagName(): String
    fun children(): HtmlElements
    fun attr(key: String): String
    fun text(): String
    fun ownText(): String
    fun outerHtml(): String
    fun html(): String
    // maybe parent(), childNodes(), etc.
}

/**
 * 从 HtmlElements 集合中的第一个元素获取文本。
 * 如果集合为空，返回空字符串。
 */
fun HtmlElements.text(): String {
    return firstOrNull()?.text() ?: ""
}