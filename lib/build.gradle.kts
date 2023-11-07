plugins {
  alias(libs.plugins.kotlin.jvm)
  `java-gradle-plugin`
}

gradlePlugin {
  plugins {
    register("lib-plugin") {
      id = "com.rickbusarow.repros.lib-plugin"
      implementationClass = "com.rickbusarow.repros.lib.LibPlugin"
    }
  }
}

dependencies {
  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
}
