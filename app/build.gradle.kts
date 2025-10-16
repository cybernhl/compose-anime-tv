plugins {
  id("com.android.application")
  kotlin("android")
  alias(libs.plugins.kotlin.parcelize)
  alias(libs.plugins.ksp)
  id("de.mannodermaus.android-junit5")
  alias(libs.plugins.jetbrains.compose)
  alias(libs.plugins.compose.compiler)
}

android {
  namespace = "com.seiko.tv.anime"
  compileSdk = 36

  buildFeatures {
    compose = true
    buildConfig = true
  }

  defaultConfig {
    applicationId = "com.seiko.tv.anime"
    minSdk = 23
    targetSdk = 36
    versionCode = 1
    versionName = "1.0.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  signingConfigs {
    getByName("debug") {
      storeFile = rootProject.file("secrets/debug-keystore.jks")
      storePassword = "123456"
      keyAlias = "compose-anime-tv"
      keyPassword = "123456"
    }
  }

  buildTypes {
    debug {
      signingConfig = signingConfigs.getByName("debug")
    }

    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }

  sourceSets {
    getByName("debug") {
      java.srcDir(File("build/generated/ksp/debug/kotlin"))
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_11.toString()
    allWarningsAsErrors = false
    freeCompilerArgs = listOf(
      "-Xopt-in=kotlin.RequiresOptIn",
      "-Xallow-unstable-dependencies"
    )
  }

  packagingOptions {
    resources {
      excludes.addAll(
        listOf(
          "META-INF/AL2.0",
          "META-INF/LGPL2.1",
          "META-INF/versions/9/OSGI-INF/MANIFEST.MF",
          "META-INF/LGPL2.1",
          "META-INF/LGPL2.1",
        )
      )
    }
    jniLibs {
      pickFirsts.addAll(
        listOf(
          "lib/arm64-v8a/libc++_shared.so",
          "lib/armeabi-v7a/libc++_shared.so",
          "lib/x86/libc++_shared.so",
          "lib/x86_64/libc++_shared.so",
        )
      )
    }
  }
}

dependencies {
  implementation(libs.kotlin.stdlib)
  implementation(project(":core"))
  implementation(project(":feature:service"))
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.activity.ktx)
  implementation(libs.androidx.activity.compose)

  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.serialization.json)

  implementation(libs.okhttp.interceptor.logging)
  implementation(libs.okhttp)
  implementation(libs.jsoup)
  implementation(libs.hson)

  implementation(libs.androidx.room.runtime)
  ksp(libs.androidx.room.compiler)
  implementation(libs.androidx.room.ktx)
  implementation(libs.androidx.room.paging)


  implementation(compose.ui)
  implementation(compose.runtime)
  implementation(compose.foundation)
  implementation(compose.animation)
  implementation(compose.material)
  implementation(compose.materialIconsExtended)
  implementation(compose.material3)
  implementation(compose.components.resources)
  implementation(compose.preview)


  implementation(libs.androidx.paging.common.ktx)
  implementation(libs.androidx.paging.runtime)
  implementation(libs.androidx.paging.compose)

  implementation(libs.jetbrains.androidx.navigation.compose   )
  implementation(libs.jetbrains.androidx.lifecycle.viewmodel  )
  implementation(libs.jetbrains.androidx.lifecycle.viewmodel.compose  )
  implementation(libs.jetbrains.androidx.lifecycle.runtime.compose )

  implementation(libs.google.accompanist.insets)
  implementation(libs.google.accompanist.systemuicontroller)
  implementation(libs.google.accompanist.pager )


  implementation(libs.coil)
  implementation(libs.coil.core)
  implementation(libs.coil.compose)
  implementation(libs.coil.network.okhttp )


  implementation(libs.koin.core)
  implementation(libs.koin.compose)
  implementation(libs.koin.android)
  implementation(libs.koin.compose.viewmodel)
  implementation(libs.koin.compose.viewmodel.navigation)

  implementation(libs.timber)
//    junit5()
//    test()
//    androidTest()

  // hidden api by pass https://github.com/tiann/FreeReflection
  implementation("com.github.tiann:FreeReflection:3.1.0")

  // player https://github.com/google/ExoPlayer
  implementation("com.google.android.exoplayer:exoplayer:2.15.0")
}
