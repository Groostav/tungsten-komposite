package groostav.wk

import kotlin.reflect.KClass
import java.lang.Class as JClass
import kotlin.reflect.full.findAnnotation


class TaskGraph {


    companion object {

        val Empty = TaskGraph()
    }
}

fun scanUserTaskObject(userTasksContainer: Any): Result<List<Task>> {

    if(userTasksContainer is KClass<*> || userTasksContainer is JClass<*>){
        return Err(Problem(

        ))
    }

    val annotated = userTasksContainer::class.members
        .filterIsInstance<kotlin.reflect.KFunction<*>>()
        .filter { it.findAnnotation<Task>() != null }
        .forEach { fn -> registerBound(userTasksContainer, fn) }
}

