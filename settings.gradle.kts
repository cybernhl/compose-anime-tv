enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
//  resolutionStrategy {
//    eachPlugin {
//      if (requested.id.namespace == "org.jetbrains.kotlin") {
//        useVersion("1.9.25")
//      }
//    }
//  }
  repositories {
    gradlePluginPortal()
    maven(url = "https://plugins.gradle.org/m2/")
    mavenCentral()
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    maven {
      url = uri("https://oss.sonatype.org/content/repositories/snapshots")
      name = "SonatypeSnapshots"
      mavenContent {
        snapshotsOnly()
      }
    }
    maven(url = "https://maven.aliyun.com/repository/gradle-plugin/")
    maven(url = "https://maven.aliyun.com/repository/spring-plugin/")
    maven(url = "https://jitpack.io")
    maven(url = "https://s3.amazonaws.com/repo.commonsware.com")
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven(url = "https://api.xposed.info/")
    maven(url = "https://jogamp.org/deployment/maven")
    maven(url = "https://developer.huawei.com/repo/")
    maven(url = "https://raw.githubusercontent.com/cybernhl/maven-repository/master/")
    maven(url = "https://maven.aliyun.com/repository/jcenter")
    maven(url = "https://maven.aliyun.com/repository/public/")
    maven(url = "https://maven.aliyun.com/repository/spring/")
    maven(url = "https://maven.aliyun.com/repository/google/")
    maven(url = "https://maven.aliyun.com/repository/grails-core/")
    maven(url = "https://maven.aliyun.com/repository/apache-snapshots/")
    maven(url = "https://packages.jetbrains.team/maven/p/skija/maven")
  }

  plugins {

  }
}

plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
val fullVersion = System.getProperty("java.version", "8.0.0")
val versionComponents = fullVersion
  .split(".")
  .take(2)
  .filter { it.isNotBlank() }
  .map { Integer.parseInt(it) }

val currentJdk = if (versionComponents[0] == 1) versionComponents[1] else versionComponents[0]

//gradle.allprojects {
//  configurations.all {
//    resolutionStrategy {
//      eachDependency {
//        if (requested.group == "org.jetbrains.kotlin") {
//          useVersion("1.9.25")
//          because("Force all Kotlin libraries to a consistent version to find conflicts.")
//        }
//      }
//    }
//  }
//}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
  repositories {
    mavenCentral()
    google()
    maven {
      url = uri("https://oss.sonatype.org/content/repositories/snapshots")
      name = "SonatypeSnapshots"
      mavenContent {
        snapshotsOnly()
      }
    }
    maven(url = "https://jitpack.io")
    maven(url = "https://s3.amazonaws.com/repo.commonsware.com")
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven(url = "https://api.xposed.info/")
    maven(url = "https://jogamp.org/deployment/maven")
    maven(url = "https://raw.githubusercontent.com/cybernhl/maven-repository/master/")
    maven(url = "https://maven.aliyun.com/repository/jcenter")
    maven(url = "https://maven.aliyun.com/repository/public/")
    maven(url = "https://maven.aliyun.com/repository/spring/")
    maven(url = "https://maven.aliyun.com/repository/google/")
    maven(url = "https://packages.jetbrains.team/maven/p/skija/maven")
  }
}

rootProject.name = "compose-anime-tv"

include(
  ":app",
  ":core",
  ":feature:service",
)
