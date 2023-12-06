@file:Suppress("UnstableApiUsage")

rootProject.name = "intermediate-one"

pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
  }
  includeBuild("../build-logic")
  includeBuild("..")
  includeBuild("../intermediate-two")
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

include(":intermediate-one-lib")
// includeBuild("../intermediate-two")
