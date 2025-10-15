import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories

fun Project.configRepository() {
  repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")
  }
}

fun DependencyHandlerScope.koin() {
  api("io.insert-koin:koin-core:${Versions.koin}")
  api("io.insert-koin:koin-android:${Versions.koin}")
  api("io.insert-koin:koin-androidx-compose:${Versions.koin}")
  api("io.insert-koin:koin-compose-viewmodel:${Versions.koin}")
}

fun DependencyHandlerScope.compose() {
  val composeBom = platform("androidx.compose:compose-bom:2025.10.00")
  implementation(composeBom)

  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-tooling")
  implementation("androidx.compose.foundation:foundation")
  implementation("androidx.compose.animation:animation")
  implementation("androidx.compose.material:material")
  implementation("androidx.compose.material:material-icons-core")
  implementation("androidx.compose.material:material-icons-extended")

  implementation("androidx.navigation:navigation-compose", Versions.navigation)
  implementation("androidx.paging:paging-compose", Versions.pagingCompose)
  implementation("com.google.accompanist:accompanist-insets", Versions.accompanist)
  implementation("com.google.accompanist:accompanist-systemuicontroller", Versions.accompanist)
  implementation("com.google.accompanist:accompanist-pager", Versions.accompanist)
}

fun DependencyHandlerScope.android() {
  lifecycle()
  api("androidx.core:core-ktx", Versions.coreKtx)
  implementation("androidx.activity:activity-ktx", Versions.activity)
  implementation("androidx.activity:activity-compose", Versions.activity)
  implementation("io.coil-kt:coil-compose", Versions.coil)
}

fun DependencyHandlerScope.lifecycle() {
  implementation("androidx.lifecycle:lifecycle-runtime-ktx", Versions.lifecycle)
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx", Versions.lifecycle)
  implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate", Versions.lifecycle)
  implementation("androidx.lifecycle:lifecycle-common-java8", Versions.lifecycle)
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose", Versions.lifecycle)
}

fun DependencyHandlerScope.kotlinCoroutines() {
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core", Versions.Kotlin.coroutines)
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android", Versions.Kotlin.coroutines)
}

fun DependencyHandlerScope.kotlinSerialization() {
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json", Versions.Kotlin.serialization)
}

fun DependencyHandlerScope.utils() {
  api("com.jakewharton.timber:timber", Versions.timber)
}

fun DependencyHandlerScope.network() {
  implementation("com.squareup.okhttp3:okhttp", Versions.okhttp)
  implementation("com.squareup.okhttp3:logging-interceptor", Versions.okhttp)
  // 🐂🍺 https://github.com/Tlaster/Hson
  implementation("com.github.qdsfdhvh:Hson:0.1.5")
  // parse html https://github.com/jhy/jsoup/
  implementation("org.jsoup:jsoup:1.13.1")
}

fun DependencyHandlerScope.room() {
  implementation("androidx.room:room-runtime", Versions.room)
  implementation("androidx.room:room-ktx", Versions.room)
  implementation("androidx.room:room-paging", Versions.room)
  ksp("androidx.room:room-compiler", Versions.room)
  androidTestImplementation("androidx.room:room-testing", Versions.room)
}

fun DependencyHandlerScope.paging() {
  implementation("androidx.paging:paging-common-ktx", Versions.paging)
}

fun DependencyHandlerScope.appCenter() {
  implementation("com.microsoft.appcenter:appcenter-analytics", Versions.appCenter)
  implementation("com.microsoft.appcenter:appcenter-crashes", Versions.appCenter)
}

fun DependencyHandlerScope.junit5() {
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.1")
}

fun DependencyHandlerScope.test() {
  testImplementation("org.mockito:mockito-core:3.11.2")
  testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test", Versions.Kotlin.coroutines)
}

fun DependencyHandlerScope.androidTest() {
  androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
  androidTestImplementation("androidx.test:core", Versions.androidx_test)
  androidTestImplementation("androidx.test:runner", Versions.androidx_test)
  androidTestImplementation("androidx.test.ext:junit", Versions.extJUnitVersion)
  androidTestImplementation("androidx.test.espresso:espresso-core", Versions.espressoVersion)
  androidTestImplementation("androidx.compose.ui:ui-test", Versions.compose)
}