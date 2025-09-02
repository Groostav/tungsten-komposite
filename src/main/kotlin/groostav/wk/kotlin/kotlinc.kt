package groostav.wk.kotlin

import groostav.wk.ClassFiles
import groostav.wk.CompiletimeClasspath
import groostav.wk.JarFiles
import groostav.wk.KotlinSourceFiles
import groostav.wk.java.JvmVersion

data class KotlinDependencies(val jars: JarFiles)

data class KotlincOutput(val classes: ClassFiles)

fun kotlinc(src: KotlinSourceFiles, compileDeps: CompiletimeClasspath, sourceVersion: KotlinVersion, targetVersion: JvmVersion): KotlincOutput {
    TODO()
}