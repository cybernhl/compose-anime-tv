package com.seiko.tv.anime.data.remote.response.sakura

data class TimelineResponse(
  val tags: List<String> = emptyList(),
  val tagAnimesList: List<TagAnimes> = emptyList(),
) {

  class TagAnimes(
    val animes: List<Anime> = emptyList()
  ) {
    override fun toString(): String {
      return animes.toString()
    }
  }

  data class Anime(
    val title: String = "",
    val href: String = "",
    val body: String = "",
    val bodyHref: String = "",
  )
}