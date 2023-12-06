@file:Suppress("UnstableApiUsage")

rootProject.name = "repros"

pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
  }
  includeBuild("build-logic")
  includeBuild("intermediate-one")
  includeBuild("intermediate-two")
}

plugins {
  id("build-settings")
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
