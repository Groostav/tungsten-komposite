package groostav.wk.java

import groostav.wk.ClassFiles
import groostav.wk.JavaSourceFiles

class JavaVersion
class JvmVersion {
    companion object {
        val CURRENT: JvmVersion get() = TODO()
    }

}

class JavaDependencies

data class JavacOutput(val classes: ClassFiles)

fun javac(src: JavaSourceFiles, compileDeps: JavaDependencies, sourceVersion: JavaVersion, targetVersion: JvmVersion): JavacOutput {
    TODO()
}