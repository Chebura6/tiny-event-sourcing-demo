package ru.quipy.api

import ru.quipy.domain.Aggregate
import java.util.UUID

data class TaskAggregate(
    val id: UUID,
    var name: String,
    var description: String,
    var status: TaskStatus,
    var executors: List<UUID> // Список идентификаторов пользователей, назначенных на задачу
) : Aggregate
