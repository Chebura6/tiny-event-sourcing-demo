package ru.quipy.logic

import ru.quipy.api.TaskStatus
import java.util.UUID

data class TaskAggregateState(
    var id: UUID? = null,
    var name: String? = null,
    var description: String? = null,
    var status: TaskStatus = TaskStatus.CREATED,
    var executors: MutableList<UUID> = mutableListOf()
)
