package groostav.wk

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

sealed class Result<out T> {
    val isOk: Boolean get() = this is Ok
    val isErr: Boolean get() = this is Err

    fun getOrNull(): T? = (this as? Ok)?.value
    fun errorOrNull(): Problem? = (this as? Err)?.problem

    inline fun <R> map(f: (T) -> R): Result<R> = when (this) {
        is Ok -> Ok(f(value))
        is Err -> this
    }

    inline fun <R> flatMap(f: (T) -> Result<R>): Result<R> = when (this) {
        is Ok -> f(value)
        is Err -> this
    }

    inline fun mapError(f: (Problem) -> Problem): Result<T> = when (this) {
        is Ok -> this
        is Err -> Err(f(problem))
    }
}

data class Ok<out T>(val value: T): Result<T>()
data class Err(val problem: Problem): Result<Nothing>()

sealed interface Problem {
    val message: String
    val details: Map<String, Any?>
    val trace: PersistentList<TraceFrame>
    val cause: Throwable?

    fun withFrame(frame: TraceFrame): Problem
    fun withFrames(frames: Iterable<TraceFrame>): Problem

    data class IllegalArgument(
        val argName: String, val functionName: String, val argIndex: Int
    ): Problem {

        // blyeh: i dont really want 'message' on the interface because its a form of rendering,
        // with the exception of a user supplied message oorrrrr maybe a message from an exception...?

        override val message: String get() = "illegal argument"
        override val details: Map<String, Any?> get() = emptyMap()
        override val trace: PersistentList<TraceFrame> get() = persistentListOf()
        override val cause: Throwable? get() = null
    }
}

data class TraceFrame(
    val phase: String,          // "while evaluating", "because"
    val subject: String,        // "compileSuperProject's second argument"
    val note: String? = null
)