package ru.quipy.api

import ru.quipy.domain.Aggregate
import java.util.UUID

data class UserAggregate(
    val id: UUID,
    var login: String,
    var passwordHash: String,
    var name: String
): Aggregate
