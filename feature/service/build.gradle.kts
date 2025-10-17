plugins {
  id("com.android.library")
  kotlin("android")
  alias(libs.plugins.room)
  alias(libs.plugins.ksp)
  id("de.mannodermaus.android-junit5")
}

android {
  namespace = "com.seiko.tv.anime.feature.service"
  compileSdk = 36

  buildFeatures {
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

  room {
    schemaDirectory("$projectDir/schemas")
  }
}

dependencies {
  implementation(libs.kotlin.stdlib)
  implementation(project(":core"))
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.serialization.json)

  implementation(libs.okhttp.interceptor.logging)
  implementation(libs.okhttp)
  implementation(libs.ktor.client.cio)
  implementation(libs.ktor.client.logging  )
  implementation(libs.jsoup)


  implementation(libs.androidx.room.runtime)
  ksp(libs.androidx.room.compiler)
  implementation(libs.androidx.room.ktx)
  implementation(libs.androidx.room.paging)
  implementation(libs.androidx.paging.common.ktx)

  implementation(libs.koin.core)
  implementation(libs.koin.android)
  implementation(libs.koin.compose)

  implementation(libs.timber)
//  junit5()
}
