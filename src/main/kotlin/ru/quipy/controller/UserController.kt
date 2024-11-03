package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.UserAggregate
import ru.quipy.logic.UserAggregateCommands
import java.security.MessageDigest
import java.util.UUID

@RestController
@RequestMapping("/users")
class UserController(private val userCommands: UserAggregateCommands) {

    @PostMapping("/register")
    fun registerUser(
        @RequestParam login: String,
        @RequestParam password: String,
        @RequestParam name: String
    ): UserAggregate {
        val passwordHash = hashPassword(password)
        return userCommands.registerUser(login, passwordHash, name)
    }

    @PostMapping("/login")
    fun loginUser(
        @RequestParam login: String,
        @RequestParam password: String
    ): Boolean {
        val passwordHash = hashPassword(password)
        return userCommands.loginUser(login, passwordHash)
    }

    private fun hashPassword(password: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val hashedBytes = md.digest(password.toByteArray())
        return hashedBytes.joinToString("") { "%02x".format(it) }
    }
}
