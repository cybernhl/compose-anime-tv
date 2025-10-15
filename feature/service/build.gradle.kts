plugins {
  id("com.android.library")
  kotlin("android")
  id("com.google.devtools.ksp").version(Versions.ksp)
  id("de.mannodermaus.android-junit5")
}

ksp {
  arg("room.schemaLocation", "$projectDir/schemas")
  arg("room.incremental", "true")
  arg("room.expandProjection", "true")
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
