package com.seiko.tv.anime.ui.common.foundation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.seiko.tv.anime.R
import timber.log.Timber
@Composable
fun NetworkImage(
  data: Any,
  modifier: Modifier = Modifier,
  contentScale: ContentScale = ContentScale.Crop,
  placeholder: @Composable (() -> Unit)? = null,
) {
  Timber.tag("NetworkImage").d("Loading image with data: $data")
  AsyncImage(
    model = data,
    contentDescription = "",
    modifier = modifier,
    contentScale = contentScale
    // 注意：Coil 3 的 AsyncImage 没有直接接收 Composable 作为 placeholder 的参数。
    // 它有 placeholder: Painter? 参数。
    // 如果需要复杂的加载中 UI，需要使用 AsyncImage 的 content 参数，如下所示：
    /*
    content = { state ->
      when (state) {
        is AsyncImagePainter.State.Loading -> {
          placeholder?.invoke()
        }
        is AsyncImagePainter.State.Success -> {
          Image(
            painter = state.painter,
            contentDescription = ...,
            contentScale = ...,
            modifier = ...
          )
        }
        else -> { // Error or Empty
          // 可以显示错误状态的UI
        }
      }
    }
    */
  )
}

