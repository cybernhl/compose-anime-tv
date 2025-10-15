plugins {
  id("com.android.application") apply false
  id("com.android.library") apply false
  id("com.diffplug.spotless").version(Versions.spotless)
}

buildscript {
  repositories {
    google()
    mavenCentral()
  }
  dependencies {
    classpath(kotlin("gradle-plugin", version = Versions.Kotlin.lang))
    classpath("de.mannodermaus.gradle.plugins:android-junit5:1.8.0.0")
  }
}

allprojects {
  configRepository()

  apply(plugin = "com.diffplug.spotless")
  spotless {
    kotlin {
      target("**/*.kt")
      targetExclude(
        "$buildDir/**/*.kt",
        "bin/**/*.kt",
        "buildSrc/**/*.kt",
        "**/*Response.kt",
      )
      ktlint(Versions.ktlint).userData(
        mapOf(
          "indent_size" to "2",
          "continuation_indent_size" to "2"
        )
      )
      // licenseHeaderFile(rootProject.file("spotless/license"))
    }
    kotlinGradle {
      target("*.gradle.kts")
      targetExclude(
        "feature/focuskit/**"
      )
      ktlint(Versions.ktlint).userData(
        mapOf(
          "indent_size" to "2",
          "continuation_indent_size" to "2"
        )
      )
    }
  }

  // 剔除livedata，使用flow代替
  configurations.all {
    exclude(group = "androidx.lifecycle", module = "lifecycle-livedata")
  }
}
