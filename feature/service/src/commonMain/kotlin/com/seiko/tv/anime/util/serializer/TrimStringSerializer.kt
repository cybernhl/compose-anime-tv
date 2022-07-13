package com.seiko.tv.anime.util.serializer

import moe.tlaster.hson.HtmlSerializer
import org.jsoup.nodes.Element

class TrimStringSerializer : HtmlSerializer<String> {
  override fun decode(element: Element, wholeText: String): String {
    return wholeText.replace("\\s".toRegex(), "").trim()
  }
}
