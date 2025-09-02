package groostav.wk.maven

import groostav.wk.Classpath
import groostav.wk.JarFiles

class ResolvedMavenDependencies(val jars: JarFiles) {

    fun asClasspath(): Classpath {
        return TODO()
    }
}

class DeclaredMavenDependencies

fun DeclaredMavenDependencies(vararg mavenTargetTriples: String): DeclaredMavenDependencies{
    return TODO()
}

operator fun DeclaredMavenDependencies.plus(other: DeclaredMavenDependencies): DeclaredMavenDependencies {
    return TODO()
}


enum class MavenSourceConfiguration { MAIN, TEST }