package com.seiko.tv.anime.util.parser

import androidx.compose.ui.semantics.text

import com.seiko.tv.anime.util.html.HtmlDocument
import com.seiko.tv.anime.util.html.HtmlElement
import com.seiko.tv.anime.util.html.HtmlElements
import com.seiko.tv.anime.util.html.HtmlParser
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

/**
 * HtmlParser 接口的 Jsoup 实现。
 * 用于在 Android 平台上替代 Hson，提供更直观的解析方式。
 */
class JsoupHtmlParser : HtmlParser {
    override fun parse(html: String): HtmlDocument {
        return parse(html, "")
    }

    override fun parse(html: String, baseUri: String): HtmlDocument {
        val doc: Document = Jsoup.parse(html, baseUri)
        return JsoupHtmlDocument(doc)
    }
}

/**
 * Jsoup 对 HtmlDocument 接口的包装实现。
 */
private class JsoupHtmlDocument(private val doc: Document) : HtmlDocument {
    override fun select(cssQuery: String): HtmlElements {
        return JsoupHtmlElements(doc.select(cssQuery))
    }

    override fun body(): HtmlElement {
        return JsoupHtmlElement(doc.body())
    }

    override fun title(): String {
        return doc.title()
    }
}

/**
 * Jsoup 对 HtmlElement 接口的包装实现。
 */
private class JsoupHtmlElement(private val el: Element) : HtmlElement {
    override fun select(cssQuery: String): HtmlElements {
        return JsoupHtmlElements(el.select(cssQuery))
    }

    override fun tagName(): String = el.tagName()
    override fun children(): HtmlElements = JsoupHtmlElements(el.children())
    override fun attr(key: String): String = el.attr(key)
    override fun text(): String = el.text()
    override fun ownText(): String = el.ownText()
    override fun outerHtml(): String = el.outerHtml()
    override fun html(): String = el.html()
}

/**
 * Jsoup 对 HtmlElements 接口的包装实现。
 * 通过 Kotlin 类委托，轻松实现 List<HtmlElement> 接口。
 */
private class JsoupHtmlElements(private val elements: Elements) : HtmlElements, List<HtmlElement> by elements.map(::JsoupHtmlElement) {
    override fun select(cssQuery: String): HtmlElements {
        return JsoupHtmlElements(elements.select(cssQuery))
    }
}
