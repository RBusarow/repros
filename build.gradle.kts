plugins {
  id("build-plugin")
  alias(libs.plugins.kotlin.jvm) apply false
  `java-base`
}

allprojects {
  group = property("GROUP") as String
  version = property("VERSION") as String

  plugins.withId("java") {
    java.toolchain {
      languageVersion.set(JavaLanguageVersion.of(11))
    }
  }
}
