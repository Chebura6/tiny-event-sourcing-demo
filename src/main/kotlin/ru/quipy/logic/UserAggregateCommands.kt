package ru.quipy.logic

import ru.quipy.api.UserAggregate
import java.util.UUID

interface UserAggregateCommands {
    fun registerUser(login: String, passwordHash: String, name: String): UserAggregate
    fun loginUser(login: String, passwordHash: String): Boolean
}
