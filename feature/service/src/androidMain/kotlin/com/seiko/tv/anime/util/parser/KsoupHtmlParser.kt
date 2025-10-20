package com.seiko.tv.anime.util.parser

import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.nodes.Document
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.select.Elements
import com.seiko.tv.anime.util.html.HtmlDocument
import com.seiko.tv.anime.util.html.HtmlElement
import com.seiko.tv.anime.util.html.HtmlElements
import com.seiko.tv.anime.util.html.HtmlParser

class KsoupHtmlParser : HtmlParser {
    override fun parse(html: String): HtmlDocument {
        return parse(html, "")
    }

    override fun parse(html: String, baseUri: String): HtmlDocument {
        val doc = Ksoup.parse(html, baseUri = baseUri)
        return KsoupHtmlDocument(doc)
    }
}

class KsoupHtmlDocument(private val doc: Document) : HtmlDocument {
    override fun select(cssQuery: String): HtmlElements {
        return KsoupHtmlElements(doc.select(cssQuery))
    }
    override fun body(): HtmlElement {
        return KsoupHtmlElement(doc.body())
    }
    override fun title(): String {
        return doc.title()
    }
}

class KsoupHtmlElement(private val el: Element) : HtmlElement {
    override fun select(cssQuery: String): HtmlElements {
        return KsoupHtmlElements(el.select(cssQuery))
    }
    override fun tagName(): String = el.tagName()
    override fun children(): HtmlElements = KsoupHtmlElements(el.children())
    override fun attr(key: String): String = el.attr(key)
    override fun text(): String = el.text()
    override fun ownText(): String = el.ownText()
    override fun outerHtml(): String = el.outerHtml()
    override fun html(): String = el.html()
}

class KsoupHtmlElements(private val elements: Elements) : HtmlElements, List<HtmlElement> by elements.map(::KsoupHtmlElement) {
    override fun select(cssQuery: String): HtmlElements {
        return KsoupHtmlElements(elements.select(cssQuery))
    }
}
