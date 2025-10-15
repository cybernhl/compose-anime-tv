import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.add(
  configurationName: String,
  name: String,
  version: String? = null
) = add(
  configurationName, "$name${if (version != null) ":$version" else ""}"
)

internal fun DependencyHandler.api(name: String, version: String? = null) =
  add("api", name, version)

internal fun DependencyHandler.implementation(dependency: Dependency) {
  add("implementation", dependency)
}

internal fun DependencyHandler.implementation(name: String, version: String? = null) =
  add("implementation", name, version)

internal fun DependencyHandler.kapt(name: String, version: String? = null) =
  add("kapt", name, version)

internal fun DependencyHandler.ksp(name: String, version: String? = null) =
  add("ksp", name, version)

internal fun DependencyHandler.testRuntimeOnly(name: String, version: String? = null) =
  add("testRuntimeOnly", name, version)

internal fun DependencyHandler.testImplementation(name: String, version: String? = null) =
  add("testImplementation", name, version)

internal fun DependencyHandler.androidTestImplementation(name: String, version: String? = null) =
  add("androidTestImplementation", name, version)