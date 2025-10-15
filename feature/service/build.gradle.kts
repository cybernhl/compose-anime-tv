plugins {
  id("com.android.library")
  kotlin("android")
  id("com.google.devtools.ksp").version(Versions.ksp)
  id("androidx.room") version "2.8.2"
  id("de.mannodermaus.android-junit5")
}

android {
  compileSdk = AndroidSdk.compile
  namespace= "com.seiko.tv.anime.feature.service"
  buildFeatures {
    buildConfig = true
  }
  compileOptions {
    sourceCompatibility = Versions.Java.java
    targetCompatibility = Versions.Java.java
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
  implementation(project(":core"))
  kotlinCoroutines()
  kotlinSerialization()
  network()
  room()
  paging()
  junit5()
}
