package com.seiko.tv.anime.util

/**
 * 为自定义的 Html 接口提供便捷的扩展函数，模拟 Jsoup/Ksoup 的流畅API。
 */

/**
 * 安全地获取集合中的第一个元素，如果集合为空则返回 null。
 */
fun <T> List<T>.firstOrNull(): T? {
    return if (isEmpty()) null else this[0]
}