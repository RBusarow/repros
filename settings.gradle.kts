@file:Suppress("UnstableApiUsage")

rootProject.name = "repros"

pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
  }
  includeBuild("build-logic")
}

dependencyResolutionManagement {

  rulesMode.set(RulesMode.FAIL_ON_PROJECT_RULES)
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

  repositories {
    mavenCentral()
    google()
  }
}

include(":lib")

includeBuild("samples")
