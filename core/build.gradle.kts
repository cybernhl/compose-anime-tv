plugins {
  id("com.android.library")
  kotlin("android")
  alias(libs.plugins.ksp)
  alias(libs.plugins.compose.compiler)
}

android {
  namespace = "com.seiko.tv.anime.core"
  compileSdk = 36

  buildFeatures {
    compose = true
    buildConfig = true
  }

  defaultConfig {
    minSdk = 23
    targetSdk = 36
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

  sourceSets {
    getByName("debug") {
      java.srcDirs(
        "build/generated/ksp/debug/kotlin",
        "src/debug/kotlin",
      )
    }
  }
}

dependencies {
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.activity.ktx)
  implementation(libs.androidx.activity.compose)

  implementation(libs.coil)
  implementation(libs.coil.core)
  implementation(libs.coil.compose)


  implementation(libs.koin.core)
  implementation(libs.koin.compose)
  implementation(libs.koin.android)
  implementation(libs.koin.compose.viewmodel)


  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.serialization.json)

  implementation(libs.timber)

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.tooling)
  implementation(libs.androidx.compose.foundation)
  implementation(libs.androidx.compose.animation)
  implementation(libs.androidx.compose.material)
  implementation(libs.androidx.compose.material)
  implementation(libs.androidx.compose.material.icons.core)
  implementation(libs.androidx.compose.material.icons.extended)

  implementation(libs.androidx.paging.compose)
  implementation(libs.androidx.navigation.compose)

  implementation(libs.google.accompanist.insets)
  implementation(libs.google.accompanist.systemuicontroller)
  implementation(libs.google.accompanist.pager )
  implementation(libs.timber)
}
