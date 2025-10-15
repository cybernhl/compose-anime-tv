package com.seiko.tv.anime.ui.composer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.seiko.tv.anime.ui.detail.DetailScene
import com.seiko.tv.anime.ui.favorite.FavoriteScene
import com.seiko.tv.anime.ui.feed.FeedScene
import com.seiko.tv.anime.ui.home.HomeScene
import com.seiko.tv.anime.ui.player.PlayerScene
import com.seiko.tv.anime.ui.tag.TagScene
import com.seiko.tv.anime.util.decodeUrl
import com.seiko.tv.anime.util.encodeUrl

@Composable
fun Router(appNavigator: AppNavigator) {
  NavHost(appNavigator.navController, startDestination = initialRoute) {
    composable(Router.Home.route) { HomeScene() }
    composable(Router.Feed.route) { FeedScene() }
    composable(Router.Detail.route) {
      DetailScene(Router.Detail.getUri(it))
    }
    composable(Router.Player.route) {
      PlayerScene(Router.Player.getUri(it))
    }
    composable(Router.Favorite.route) { FavoriteScene() }
    composable(Router.TagPage.route) {
      TagScene(Router.TagPage.getUri(it))
    }
  }
}

sealed class Router(val route: String) {

  data object Home : Router("/home")
  data object Feed : Router("/feed")
  data object Favorite : Router("/favorite")

  data class Detail(val uri: String) : Router(route.replace("{uri}", uri.encodeUrl())) {
    companion object {
      // 路由模式供 NavGraphBuilder 使用
      const val route = "/detail?uri={uri}"
      fun getUri(entry: NavBackStackEntry): String {
        return entry.arguments?.getString("uri")?.decodeUrl() ?: ""
      }
    }
  }

  data class Player(val uri: String) : Router(route.replace("{uri}", uri.encodeUrl())) {
    companion object {
      const val route = "/player?uri={uri}"
      fun getUri(entry: NavBackStackEntry): String {
        return entry.arguments?.getString("uri")?.decodeUrl() ?: ""
      }
    }
  }

  data class TagPage(val uri: String) : Router(route.replace("{uri}", uri.encodeUrl())) {
    companion object {
      const val route = "/tagpage?uri={uri}"
      fun getUri(entry: NavBackStackEntry): String {
        return entry.arguments?.getString("uri")?.decodeUrl() ?: ""
      }
    }
  }

//  class Home : Router("/home") {
//    // 為了讓 Router.Home.route 能夠被訪問，我們需要一個伴生對象
//    companion object {
//      const val route = "/home"
//    }
//  }
//
//  class Feed : Router("/feed") {
//    companion object {
//      const val route = "/feed"
//    }
//  }
//
//  // 對於已經是 class 的，我們需要將 route 字串移到伴生對象中
//  class Detail private constructor(route: String) : Router(route) {
//    companion object {
//      const val route = "/detail?uri={uri}"
//      fun getUri(entry: NavBackStackEntry): String {
//        return entry.arguments?.getString("uri")?.decodeUrl() ?: ""
//      }
//      operator fun invoke(uri: String): String {
//        return route.replace("{uri}", uri.encodeUrl())
//      }
//    }
//  }
//
//  class Player private constructor(route: String) : Router(route) {
//    companion object {
//      const val route = "/player?uri={uri}"
//      fun getUri(entry: NavBackStackEntry): String {
//        return entry.arguments?.getString("uri")?.decodeUrl() ?: ""
//      }
//      operator fun invoke(uri: String): String {
//        return route.replace("{uri}", uri.encodeUrl())
//      }
//    }
//  }
//
//  class Favorite : Router("/favorite") {
//    companion object {
//      const val route = "/favorite"
//    }
//  }
//
//  class TagPage private constructor(route: String) : Router(route) {
//    companion object {
//      const val route = "/tagpage?uri={uri}"
//      fun getUri(entry: NavBackStackEntry): String {
//        return entry.arguments?.getString("uri")?.decodeUrl() ?: ""
//      }
//      operator fun invoke(uri: String): String {
//        return route.replace("{uri}", uri.encodeUrl())
//      }
//    }
//  }

}

private val initialRoute = Router.Home.route
