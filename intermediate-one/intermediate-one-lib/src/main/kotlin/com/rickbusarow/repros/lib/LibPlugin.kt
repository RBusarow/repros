package com.rickbusarow.repros.lib

import org.gradle.api.Plugin
import org.gradle.api.Project

open class LibPlugin : Plugin<Project> {
  override fun apply(target: Project) {

    println(
      """
            |\|\,'\,'\ ,.
            )        ;' |,'
           /              |,'|,.
          /                  ` /__
         ,'                    ,-'
        ,'                    :
       (_                     '
     ,'                      ;
     |---._ ,'     .        '
     :   o Y---.__  ;      ;
     /`,""-|     o`.|     /
    ,  `._  `.    ,'     ;
    ;         `""'      ;
   /                   -'.
   \                   G  )
    `-.__________,   `._,'
            (`   `     |)\
           / `.       ,'  \
          /    `-----'     \
         /
      """.trimIndent()
    )
  }
}
