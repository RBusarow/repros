plugins {
  base
  id("build-plugin")
  id("com.rickbusarow.repros.lib-plugin")
  `java-base`
}

tasks.named("check") {
  doLast {
    println("Hello from intermediate-two/build.gradle.kts")
  }
}

java.toolchain {
  languageVersion.set(JavaLanguageVersion.of(11))
}
