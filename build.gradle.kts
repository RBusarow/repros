plugins {
  alias(libs.plugins.kotlin.jvm) apply false
  `java-base`
}

allprojects {
  plugins.withId("java") {
    java.toolchain {
      languageVersion.set(JavaLanguageVersion.of(11))
    }
  }
}

tasks.named("check") {
  gradle.includedBuilds.forEach { build ->
    dependsOn(build.task(":check"))
  }
  doLast {
    println("Hello from lib/build.gradle.kts")
  }
}
