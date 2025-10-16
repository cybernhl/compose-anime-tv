buildscript {
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
    dependencies {
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false

    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.kotlin.serialization) apply false

    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.de.ktorfit) apply false

    alias(libs.plugins.room) apply false

    alias(libs.plugins.android.lint) apply false

    alias(libs.plugins.android.junit5) apply false

//    id("com.diffplug.spotless").version(Versions.spotless)
}

allprojects {
//  apply(plugin = "com.diffplug.spotless")
//  spotless {
//    kotlin {
//      target("**/*.kt")
//      targetExclude(
//        "$buildDir/**/*.kt",
//        "bin/**/*.kt",
//        "buildSrc/**/*.kt",
//        "**/*Response.kt",
//      )
//      ktlint(Versions.ktlint).userData(
//        mapOf(
//          "indent_size" to "2",
//          "continuation_indent_size" to "2"
//        )
//      )
//      // licenseHeaderFile(rootProject.file("spotless/license"))
//    }
//    kotlinGradle {
//      target("*.gradle.kts")
//      targetExclude(
//        "feature/focuskit/**"
//      )
//      ktlint(Versions.ktlint).userData(
//        mapOf(
//          "indent_size" to "2",
//          "continuation_indent_size" to "2"
//        )
//      )
//    }
//  }
//
    // 剔除livedata，使用flow代替
    configurations.all {
        exclude(group = "androidx.lifecycle", module = "lifecycle-livedata")
    }
}
