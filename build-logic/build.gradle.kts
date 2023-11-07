plugins {
  alias(libs.plugins.kotlin.jvm)
  `java-gradle-plugin`
}

gradlePlugin {
  plugins {
    register("build-plugin") {
      id = "build"
      implementationClass = "builds.BuildPlugin"
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
