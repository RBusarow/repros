package builds

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.api.initialization.dsl.VersionCatalogBuilder
import org.gradle.api.internal.file.FileOperations
import javax.inject.Inject

abstract class SettingsPlugin @Inject constructor(
  private val fileOperations: FileOperations
) : Plugin<Settings> {

  override fun apply(target: Settings) {

    target.dependencyResolutionManagement {
      it.versionCatalogs { container ->

        val catalogBuilder = container.findByName("libs")
          ?: container.create("libs") { builder ->

            val maybeFile = target.rootDir.resolve("../gradle/libs.versions.toml")
              .takeIf { it.exists() }

            if (maybeFile != null) {
              builder.from(fileOperations.immutableFiles(maybeFile))
            }
          }

        @Suppress("UnstableApiUsage")
        target.providers
          .systemPropertiesPrefixedBy("override_")
          .get()
          .forEach { (key, value) ->
            val alias = key.substring("override_".length)
            catalogBuilder.overrideVersion(alias = alias, versionString = value.toString())

            if (alias == "kotlin" && value.toString().startsWith("1.8")) {
              // TODO hardcoded to match what's in libs.versions.toml, but kinda ugly
              catalogBuilder.overrideVersion(alias = "ksp", versionString = "$value-1.0.11")
            }
          }
      }
    }
  }

  private fun VersionCatalogBuilder.overrideVersion(alias: String, versionString: String) {
    println("Overriding $alias with $versionString")
    version(alias, versionString)
  }
}
