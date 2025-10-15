plugins {
  id("com.android.library")
  kotlin("android")
  id("com.google.devtools.ksp").version(Versions.ksp)
}

android {
  compileSdk = AndroidSdk.compile
  namespace= "com.seiko.tv.anime"
  defaultConfig {
    minSdk = AndroidSdk.min
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
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.15"
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
  koin()
  android()
  kotlinCoroutines()
  utils()
  compose()
}
