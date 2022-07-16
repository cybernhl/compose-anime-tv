package com.seiko.tv.anime.ui.detail

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.seiko.tv.anime.model.anime.AnimeTag
import com.seiko.tv.anime.ui.theme.AnimeTvTheme

@Preview(widthDp = 1280, heightDp = 400)
@Composable
private fun DetailAnimeInfoPreview() {
  AnimeTvTheme {
    Surface(color = MaterialTheme.colorScheme.background) {
      DetailAnimeInfo(
        title = "魔法纪录 魔法少女小圆外传 第二季 -觉醒前夜-",
        releaseTime = "日本",
        state = "更新至第6集",
        tags = listOf("搞笑", "奇幻", "百合", "治愈").map { AnimeTag(it, "") },
        types = emptyList(),
        indexes = emptyList(),
        description = "《魔法纪录魔法少女小圆外传第二季-觉醒前夜-》TV动画《魔法少女小圆☆魔法少女小圆外传2ndSEASON-觉醒前夜-》的放送决定于7月31日（周六）24:00开始！在第二季SEASON放送之前，7月3日（周六）开始将连续3周播放第一季SEASON的总集篇，7月24日（周六）将播出第二季SEASON之前的特别节目！此外，FinalSeason-浅梦之晓预计将于2021年末播出！作为实现愿望的代价，在无人知晓的情况下不断战斗的魔法少女们。然而，环伊吕波，却忘记了自己的愿望。“成为魔法少女的时候，我是许了什么愿望来着？”日常当中突然出现的空洞。失去了的某件重要之物。连理由都不知道，就这样每天不停地战斗……就在此时，魔法少女之间开始流传某个传闻。“若能去神滨的话，魔法少女就能得到拯救”。魔法少女与传闻集中的城市，神滨市。寻求着自己失去的愿望，环伊吕波的故事开始了——"
      )
    }
  }
}

@Preview(widthDp = 1280, heightDp = 400)
@Composable
private fun DetailAnimeInfoV2Preview() {
  AnimeTvTheme {
    Surface(color = MaterialTheme.colorScheme.background) {
      DetailAnimeInfo(
        title = "境界触发者 第三季",
        releaseTime = "日本",
        state = "更新至第1集",
        tags = listOf("日语", "tv").map { AnimeTag(it, "") },
        types = emptyList(),
        indexes = emptyList(),
        description = "《境界触发者第三季》TV动画《境界触发者》第3季制作决定！"
      )
    }
  }
}
