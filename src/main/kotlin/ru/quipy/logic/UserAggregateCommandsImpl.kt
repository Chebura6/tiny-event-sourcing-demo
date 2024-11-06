package ru.quipy.logic

import org.springframework.stereotype.Component
import ru.quipy.api.UserAggregate
import java.util.UUID

@Component
class UserAggregateCommandsImpl : UserAggregateCommands {

    private val users = mutableMapOf<String, UserAggregate>()

    override fun registerUser(login: String, passwordHash: String, name: String): UserAggregate {
        if (users.containsKey(login)) {
            throw IllegalArgumentException("User with login $login already exists")
        }

        val newUser = UserAggregate(
            id = UUID.randomUUID(),
            login = login,
            passwordHash = passwordHash,
            name = name
        )

        users[login] = newUser
        return newUser
    }

    override fun loginUser(login: String, passwordHash: String): Boolean {
        val user = users[login]
        return user?.passwordHash == passwordHash
    }
}
