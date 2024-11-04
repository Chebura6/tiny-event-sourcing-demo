package ru.quipy.api

import ru.quipy.core.annotations.AggregateType
import ru.quipy.domain.Aggregate
import java.util.UUID

@AggregateType(aggregateEventsTableName = "aggregate-task")
data class TaskAggregate(
    val id: UUID,
    var name: String,
    var description: String,
    var status: TaskStatus,
    var executors: List<UUID>
) : Aggregate
