package com.seiko.tv.anime

import com.seiko.tv.anime.data.remote.response.sakura.DetailResponse
import com.seiko.tv.anime.data.remote.response.sakura.HomeResponse
import com.seiko.tv.anime.data.remote.response.sakura.TagResponse
import com.seiko.tv.anime.data.remote.response.sakura.TimelineResponse
import com.seiko.tv.anime.data.remote.response.sakura.VideoResponse
import com.seiko.tv.anime.util.html.HtmlDocument
import com.seiko.tv.anime.util.html.attr
import com.seiko.tv.anime.util.html.text
import timber.log.Timber

/**
 * 从 Ksoup 的 HtmlDocument 中提取数据并组装成 Response 数据类的工具。
 * 这里的逻辑是根据原有 Hson 注解手动实现的。
 */

// 1. 实现 HomeResponse 的提取
fun extractHomeResponse(doc: HtmlDocument): HomeResponse {
    val tabs = doc.select(".menu ul.dmx > li").map { el ->
        HomeResponse.AnimeTab(
            title = el.select("a").text(),
            href = el.select("a").attr("href")
        )
    }

    // Hson 使用了两个选择器，我们依次尝试
    val titlesElements = doc.select("div.firs div.dtit > h2 > a").ifEmpty {
        doc.select("div.area div.dtit > h2")
    }
    val titles = titlesElements.map { it.text() }

    val groupsElements = doc.select("div.firs div.img").ifEmpty {
        doc.select("div.area div.imgs")
    }
    val groups = groupsElements.map { groupEl ->
        val animes = groupEl.select(".img > ul > li, ul > li").map { animeEl ->
            // *** 修正这里的构造函数调用 ***
            HomeResponse.Anime(
                title = animeEl.select("img").attr("alt"),
                cover = animeEl.select("img").attr("src"),
                href = animeEl.select("a").attr("href")
            )
        }
        HomeResponse.AnimeGroup(animes = animes)
    }

    return HomeResponse(tabs, titles, groups)
}

// 2. 实现 DetailResponse 的提取
fun extractDetailResponse(doc: HtmlDocument): DetailResponse {
    val title = doc.select("h1").firstOrNull()?.text() ?: ""
    val cover = doc.select("div.thumb > img").firstOrNull()?.attr("src") ?: ""

    // Hson 使用了 TrimSplitStringSerializer，这里模拟它的核心功能（去除"别名："并trim）
    val alias = doc.select("div.sinfo > p").firstOrNull()?.text()?.replace("别名：", "")?.trim() ?: ""
    val rating = doc.select("div.score > em").firstOrNull()?.text()?.toFloatOrNull() ?: 0.0f

    val tags = doc.select("div.sinfo > span").map { tagEl ->
        val tip = tagEl.ownText().trim() // e.g., "地区:", "年代:"
        val titles = tagEl.select("a").map { it.text() }
        val hrefs = tagEl.select("a").map { it.attr("href") }
        DetailResponse.Tag(tip = tip, titles = titles, hrefs = hrefs)
    }

    // Hson 使用了 TrimStringSerializer，这里直接调用 .trim()
    val description = doc.select("div.info").firstOrNull()?.text()?.trim() ?: ""

    val episodeList = doc.select("div.movurl > ul > li").map { epEl ->
        DetailResponse.Episode(
            title = epEl.select("a").text(),
            href = epEl.select("a").attr("href")
        )
    }

    val relatedList = doc.select("div.pics > ul > li").map { relEl ->
        DetailResponse.Anime(
            title = relEl.select("img").attr("alt"),
            cover = relEl.select("img").attr("src"),
            href = relEl.select("a").attr("href")
        )
    }

    return DetailResponse(title, cover, alias, rating, tags, description, episodeList, relatedList)
}

// 3. 实现 TagResponse 的提取
fun extractTagResponse(doc: HtmlDocument): TagResponse {
    val title = doc.select("div.gohome > h1").firstOrNull()?.text() ?: ""

    val animes = doc.select("div.lpic > ul > li").map { animeEl ->
        // 注解是 eq = 1，所以取第二个span
        val tagsEl = animeEl.select("span").getOrNull(1)
        val tag = if (tagsEl != null) {
            TagResponse.Tag(
                titles = tagsEl.select("label").map { it.text() },
                hrefs = tagsEl.select("a").map { it.attr("href") }
            )
        } else {
            TagResponse.Tag() // 如果找不到，返回一个空的Tag对象
        }

        TagResponse.Anime(
            title = animeEl.select("h2 > a").attr("title"),
            cover = animeEl.select("img").attr("src"),
            href = animeEl.select("h2 > a").attr("href"),
            update = animeEl.select("font").firstOrNull()?.text() ?: "",
            tags = tag,
            description = animeEl.select("p").firstOrNull()?.text() ?: ""
        )
    }

    return TagResponse(title, animes)
}

// 4. 实现 TimelineResponse 的提取
fun extractTimeLineResponse(doc: HtmlDocument): TimelineResponse {
    val tags = doc.select("div.side div.tag > span").map { it.text() }

    val tagAnimesList = doc.select("div.side div.tlist > ul").map { ulEl ->
        val animes = ulEl.select("li").map { liEl ->
            // 注解是 > a，表示直接子元素
            val aTag = liEl.children().firstOrNull { it.tagName() == "a" }
            TimelineResponse.Anime(
                title = aTag?.attr("title") ?: "",
                href = aTag?.attr("href") ?: "",
                body = aTag?.text() ?: "",
                bodyHref = aTag?.attr("href") ?: ""
            )
        }
        TimelineResponse.TagAnimes(animes = animes)
    }

    return TimelineResponse(tags, tagAnimesList)
}

// 5. 实现 VideoResponse 的提取
fun extractVideoResponse(doc: HtmlDocument): VideoResponse {
    val playUrlData = doc.select("div.bofang > div").firstOrNull()?.attr("data-vid") ?: ""

    // Hson 使用了 PlayUrlStringSerializer，我们在这里手动实现它的逻辑
    val finalPlayUrl = playUrlData.split('$').firstOrNull() ?: ""

    Timber.tag("VideoResponseExtractor").d("Extracted 'data-vid': $playUrlData, Final URL: $finalPlayUrl")

    return VideoResponse(playUrl = finalPlayUrl)
}
