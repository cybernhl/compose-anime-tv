package com.seiko.tv.anime.util.html

interface HtmlElements : List<HtmlElement> {
    fun select(cssQuery: String): HtmlElements
}

/**
 * 从 HtmlElements 集合中的第一个元素获取指定属性的值。
 * 如果集合为空，返回空字符串。
 */
fun HtmlElements.attr(key: String): String {
    return firstOrNull()?.attr(key) ?: ""
}