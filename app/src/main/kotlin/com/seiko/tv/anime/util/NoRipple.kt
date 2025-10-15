package com.seiko.tv.anime.util

import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.graphics.drawscope.ContentDrawScope

/**
 * 无波纹点击
 * Ref : https://developer.android.com/develop/ui/compose/touch-input/user-interactions/migrate-indication-ripple
 * 迁移到新的 IndicationNodeFactory API 以解决废弃警告。
 * 新的实现更高效，因为它直接在 Modifier 链中工作，避免了 `remember` 和 `LaunchedEffect` 的开销。
 */
object NoRippleIndication : Indication, IndicationNodeFactory {

  private object NoRippleIndicationNode : Modifier.Node(), DrawModifierNode {
    override fun ContentDrawScope.draw() {
      // 只绘制原始内容，不添加任何视觉效果
      drawContent()
    }
  }

  override fun create(interactionSource: InteractionSource): DelegatableNode {
    // 为所有实例返回同一个共享的、无状态的 Node
    return NoRippleIndicationNode
  }

  // 为保证单例对象的正确性，重写 equals 和 hashCode
  override fun equals(other: Any?): Boolean {
    return other === this
  }

  override fun hashCode(): Int {
    return -1
  }
}
