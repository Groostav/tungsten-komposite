@file:DependsOn("groostav:tungsten-komposite:0.1-SNAPSHOT")

import groostav.wk.Task
import groostav.wk.*
import java.nio.file.Paths;
import kotlin.io.path.exists

@Task fun hello() {
    println("hi from local jars")

    DefaultTasks.javac("src")
}


//println("pwd is ${Paths.get(".").toAbsolutePath()}")
//val root = Paths.get("../../../../").normalize()
//println("target is at ${root.toAbsolutePath()}; exists=${root.exists()}")
//
//val snapshot = root.resolve("target/tungsten-komposite-1.0-SNAPSHOT.jar")
//println("snapshot-jar is $snapshot; exists=${snapshot.exists()}")
//
//val fullyRelative = Paths.get("../../../../target/tungsten-komposite-1.0-SNAPSHOT.jar").normalize()
//println("fully-relative path is $fullyRelative; exists=${fullyRelative.exists()}")

val r = ::hello

r.call()

val x = 4;
