plugins {
  alias(libs.plugins.kotlin.jvm)
  `java-gradle-plugin`
}

dependencies {
  api(libs.google.guava)
}

gradlePlugin {
  plugins {
    register("build-plugin") {
      id = "build-plugin"
      implementationClass = "builds.BuildPlugin"
    }
    register("build-settings") {
      id = "build-settings"
      implementationClass = "builds.SettingsPlugin"
    }
  }
}

tasks.named("check") {
  doLast {
    println("Hello from build-logic/build.gradle.kts")
  }
}

java.toolchain {
  languageVersion.set(JavaLanguageVersion.of(11))
}
