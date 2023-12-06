plugins {
  alias(libs.plugins.kotlin.jvm)
  id("com.rickbusarow.repros.lib-plugin")
}

dependencies {
  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
}
