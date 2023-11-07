plugins {
  base
  id("com.rickbusarow.repros.lib-plugin")
  `java-base`
}

tasks.named("check") {
  doLast {
    println("Hello from samples/build.gradle.kts")
  }
}

java.toolchain {
  languageVersion.set(JavaLanguageVersion.of(11))
}
