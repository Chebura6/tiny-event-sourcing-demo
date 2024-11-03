package ru.quipy.api

import java.util.UUID

sealed class TaskEvent

data class NewTaskCreatedEvent(
    val id: UUID,
    val name: String,
    val description: String
) : TaskEvent()

data class TaskUpdatedEvent(
    val id: UUID,
    val name: String?,
    val description: String?,
    val status: TaskStatus
) : TaskEvent()

data class ExecutorsAssignedEvent(
    val taskId: UUID,
    val executors: List<UUID>
) : TaskEvent()
