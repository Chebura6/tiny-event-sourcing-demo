package ru.quipy.logic

import ru.quipy.api.TaskAggregate
import ru.quipy.api.TaskStatus
import java.util.UUID

interface TaskAggregateCommands {
    fun createTask(name: String, description: String, executors: List<UUID>): TaskAggregate
    fun updateTask(id: UUID, name: String?, description: String?, status: TaskStatus)
    fun assignExecutors(taskId: UUID, executors: List<UUID>)
}
