import org.gradle.api.internal.initialization.ClassLoaderScope
import org.gradle.api.internal.plugins.DefaultPluginRegistry
import org.gradle.internal.service.ServiceRegistry
import org.gradle.invocation.DefaultGradle
import org.gradle.plugin.use.internal.DefaultPluginRequestApplicator
import kotlin.jvm.optionals.getOrNull

plugins {
  base
  id("build-plugin")
  id("com.rickbusarow.repros.lib-plugin")
  `java-base`
}

java.toolchain {
  languageVersion.set(JavaLanguageVersion.of(11))
}

val gradleClass = DefaultGradle::class.java
val internalServicesField = gradleClass.getDeclaredField("services")
internalServicesField.isAccessible = true
val internalServices = internalServicesField.get(gradle) as ServiceRegistry

val pluginRequestApplicator = internalServices.get(DefaultPluginRequestApplicator::class.java)

pluginRequestApplicator

val registry = DefaultPluginRequestApplicator::class.java.getDeclaredField("pluginRegistry")
  .also { it.isAccessible = true }
  .get(pluginRequestApplicator) as DefaultPluginRegistry

val classLoaderScope = DefaultPluginRegistry::class.java
  .getDeclaredField("classLoaderScope")
  .also { it.isAccessible = true }
  .get(registry) as ClassLoaderScope

val classMappings = DefaultPluginRegistry::class.java
  .getDeclaredField("classMappings")
  .also { it.isAccessible = true }
  .get(registry) // as LoadingCache<*, *>

val cmMap = classMappings.javaClass.getMethod("asMap")
  .also { it.isAccessible = true }
  .invoke(classMappings) as Map<*, *>

val idMappings = DefaultPluginRegistry::class.java
  .getDeclaredField("idMappings")
  .also { it.isAccessible = true }
  .get(registry) // as LoadingCache<*, *>

val idMap = idMappings.javaClass.getMethod("asMap")
  .also { it.isAccessible = true }
  .invoke(idMappings) as Map<*, *>

println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
println(cmMap.toList().map { it.toString() }.sorted().joinToString("\n"))
println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
println(
  idMap.toList().map { (key, value) ->

    val id = key!!::class.java.getMethod("getId")
      .also { it.isAccessible = true }.invoke(key)

    val opt = (value as java.util.Optional<*>).getOrNull()

    val impl = if (opt == null) {
      null
    } else {
      opt::class.java.getMethod("getDisplayName").invoke(opt)
    }

    "${id.toString().padStart(50)} -- ${impl.toString().padEnd(50)} -- $value"
  }
    .sorted()
    .joinToString("\n")
)
println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
// println((idMappings as com.google.common.cache.Cache<*, *>).asMap().toList().joinToString("\n"))
println(
  "############ classLoaderScope -- ${classLoaderScope::class.java.name}  --  ${classLoaderScope.localClassLoader::class.qualifiedName}"
)
println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
