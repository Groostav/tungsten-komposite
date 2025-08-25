package groostav.wk

@Target(AnnotationTarget.FUNCTION)
annotation class Task

object DefaultTasks {

    fun javac(x: Any): Unit {
        println("javac is not running!")
    }


}

fun main(args: Array<String>) {
    println("running!")
}

@Task fun stuff(): Unit = Unit