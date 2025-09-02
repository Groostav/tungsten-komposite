@file:DependsOn("groostav:tungsten-komposite:0.1-SNAPSHOT")

import groostav.wk.*
import groostav.wk.DefaultTasks.resolvedMavenDependencies
import groostav.wk.junit.JUnit5TestOutput
import groostav.wk.maven.*


object Build {

    @Task
    fun generateCompileDeps(): DeclaredMavenDependencies = DeclaredMavenDependencies(
        "org.jetbrains.kotlin:kotlin-stdlib:2.1.10",
        "ai.koog:koog-agents-jvm:0.3.0",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2"
    )

    @Task
    fun generateTestDeps(compiletimeDeps: DeclaredMavenDependencies): @Test DeclaredMavenDependencies {
        return compiletimeDeps + DeclaredMavenDependencies(
            "org.jetbrains.kotlin:kotlin-test-junit5:2.1.10",
            "org.junit.jupiter:junit-jupiter:5.10.0"
        )
    }

    @Task
    fun resolveDeps(compiletimeDeps: DeclaredMavenDependencies): ResolvedMavenDependencies =
        DefaultTasks.resolvedMavenDependencies(compiletimeDeps)

    @Task
    fun resolveTestDeps(compiletimeDeps: @Test DeclaredMavenDependencies): @Test ResolvedMavenDependencies =
        DefaultTasks.resolvedMavenDependencies(compiletimeDeps)

    @Task
    fun compileMainKotlin(src: KotlinSourceFiles, deps: ResolvedMavenDependencies): ClassFiles = DefaultTasks.kotlinc(
        src = KotlinSourceFiles.fromMavenConventionInCwd(MavenSourceConfiguration.MAIN),
        compileDeps = deps,
    )

    @Task
    fun compileTestKotlin(deps: @Test ResolvedMavenDependencies): @Test ClassFiles = DefaultTasks.kotlinc(
        src = KotlinSourceFiles.fromMavenConventionInCwd(MavenSourceConfiguration.TEST),
        compileDeps = deps,
    )

    @Task
    fun runTests(classes: @Test ClassFiles): JUnit5TestOutput = DefaultTasks.junit5(
        classpath = classes
    )
}

fun main() = wk {
    registerAll(Build)
    dispatch(args)
}