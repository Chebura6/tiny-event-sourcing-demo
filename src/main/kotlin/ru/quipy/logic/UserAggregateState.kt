package ru.quipy.logic

import ru.quipy.api.UserAggregate
import java.util.UUID

data class UserAggregateState(
    var id: UUID? = null,
    var login: String? = null,
    var passwordHash: String? = null,
    var name: String? = null
)
