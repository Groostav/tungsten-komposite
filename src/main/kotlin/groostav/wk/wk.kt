package groostav.wk

import groostav.wk.java.JavaDependencies
import groostav.wk.java.JavaVersion
import groostav.wk.java.JvmVersion
import groostav.wk.junit.JUnit5TestOutput
import groostav.wk.maven.DeclaredMavenDependencies
import groostav.wk.maven.ResolvedMavenDependencies
import kotlin.reflect.KFunction

@Target(AnnotationTarget.FUNCTION)
annotation class Task

object DefaultTasks {

    @Task fun javac(
        src: JavaSourceFiles,
        compileDeps: JavaDependencies,
        sourceVersion: JavaVersion,
        targetVersion: JvmVersion
    ): ClassFiles {

        val output = groostav.wk.java.javac(src, compileDeps, sourceVersion, targetVersion)

        return output.classes
    }

    @Task fun kotlinc(
        src: KotlinSourceFiles,
        compileDeps: ResolvedMavenDependencies,
        sourceVersion: KotlinVersion = KotlinVersion.CURRENT,
        targetVersion: JvmVersion = JvmVersion.CURRENT
    ): ClassFiles {
        val output = groostav.wk.kotlin.kotlinc(
            src,
            CompiletimeClasspath(compileDeps.asClasspath()),
            sourceVersion,
            targetVersion
        )

        return output.classes
    }

    @Task fun junit5(classpath: ClassFiles): JUnit5TestOutput {
        return groostav.wk.junit.junit5()
    }

    @Task fun resolvedMavenDependencies(declarations: DeclaredMavenDependencies): ResolvedMavenDependencies {
        return TODO()
    }
}

data class TargetInfo(val name: String, val description: String)

class ExecutionResult{}

class TungstenKompositeRuntime internal constructor(){

    private var taskGraph: TaskGraph = TaskGraph.Empty

    fun registerAll(holder: Any): Int {
        throwOnError {

            val tasksOrErr = scanUserTaskObject(holder)

            val updatedGraphOrErr = tasksOrErr.flatMap { tasks ->
                taskGraph.addTasks(tasks)
            }

            updatedGraphOrErr.map { newGraph ->
                val added = newGraph.size() - taskGraph.size()
                taskGraph = newGraph

                added
            }
        }
    }
    fun register(fn: KFunction<*>) { /* cache signature + bound invoker */ }

    fun listTargets(): List<TargetInfo> = TODO() /* from registry */
    fun planByName(name: String): TaskGraph = TODO() /* resolve only */

    fun runByName(name: String): ExecutionResult = TODO() /* resolve+execute */
    fun runManyByName(names: List<String>): ExecutionResult = TODO() /* union graph, execute */

    fun dispatch(args: Array<String>) {
        TODO()
    }

    fun close() { /* cleanup temp stores */ }

    private fun printHelp() {
    }
}

private fun requireArg(args: Array<String>, index: Int): String {

}

fun wk(block: TungstenKompositeRuntime.() -> Unit){

    val runtime = TungstenKompositeRuntime()
    try {
        runtime.block()
    }
    catch(ex: Exception){
        throw ex
    }
}