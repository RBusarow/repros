@file:Suppress("UnstableApiUsage")

rootProject.name = "build-logic"

pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
  }
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
