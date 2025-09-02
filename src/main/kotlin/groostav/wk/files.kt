package groostav.wk

import groostav.wk.maven.MavenSourceConfiguration

interface Files

interface SourceFiles: Files
interface BinaryFiles: Files

interface Classpath
interface CompiletimeClasspath: Classpath
fun CompiletimeClasspath(classpath: Classpath): CompiletimeClasspath = TODO()
interface RuntimeClasspath: Classpath

//java specific
interface JavaSourceFiles: SourceFiles


interface KotlinSourceFiles: SourceFiles {

    companion object {

        fun fromMavenConventionInCwd(config: MavenSourceConfiguration = MavenSourceConfiguration.MAIN): KotlinSourceFiles {
            return TODO()
        }
    }
}

interface ClassFiles: BinaryFiles {
    fun asClasspath(): Classpath = TODO()
}
interface JarFiles: BinaryFiles
