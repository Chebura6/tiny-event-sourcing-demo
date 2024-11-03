package ru.quipy.api

import java.util.UUID

sealed class UserEvent

data class UserRegisteredEvent(
    val id: UUID,
    val login: String,
    val name: String
) : UserEvent()

data class UserLoginAttemptedEvent(
    val login: String,
    val success: Boolean
) : UserEvent()
