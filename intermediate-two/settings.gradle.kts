@file:Suppress("UnstableApiUsage")

rootProject.name = "intermediate-two"

pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
  }
  includeBuild("../build-logic")
  includeBuild("..")
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

include(":intermediate-two-lib")
