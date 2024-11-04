package ru.quipy.api

import ru.quipy.core.annotations.AggregateType
import ru.quipy.domain.Aggregate
import java.util.UUID

@AggregateType(aggregateEventsTableName = "aggregate-user")
data class UserAggregate(
    val id: UUID,
    var login: String,
    var passwordHash: String,
    var name: String
): Aggregate
