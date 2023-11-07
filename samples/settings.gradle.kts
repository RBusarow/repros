@file:Suppress("UnstableApiUsage")

rootProject.name = "samples"

pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
  }
  includeBuild("../build-logic")
  includeBuild("..")
}

dependencyResolutionManagement {

  rulesMode.set(RulesMode.FAIL_ON_PROJECT_RULES)
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

  versionCatalogs {
    register("libs") {
      from(files("../gradle/libs.versions.toml"))
    }
  }

  repositories {
    mavenCentral()
    google()
  }
}

include(":sample-lib")
