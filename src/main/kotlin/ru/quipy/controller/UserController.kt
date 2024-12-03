package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.UserAggregate
import ru.quipy.core.EventSourcingService
import ru.quipy.logic.UserAggregateCommands
import ru.quipy.logic.UserAggregateState
import ru.quipy.projection.UserProjection
import ru.quipy.repository.UserProjectionRepository
import ru.quipy.service.ProjectionsService
import java.security.MessageDigest
import java.util.UUID

@RestController
@RequestMapping("/users")
class UserController(
    private val userCommands: UserAggregateCommands,
    val userEsService: EventSourcingService<UUID, UserAggregate, UserAggregateState>,
    val userProjectionRepository: UserProjectionRepository,
    val projectionsService: ProjectionsService
) {

    @PostMapping("/register")
    fun registerUser(
        @RequestParam login: String,
        @RequestParam password: String,
        @RequestParam name: String
    ): UserAggregate {
        val passwordHash = hashPassword(password)
        return userCommands.registerUser(login, passwordHash, name)
    }

    @GetMapping("/getUsers")
    fun getUsers(): List<UserProjection> {
        return userProjectionRepository.findAll()
    }

    @GetMapping("/getUserById/{userId}")
    fun getUserById(@PathVariable userId: UUID): UserProjection {
        return userProjectionRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("User not found") }
    }

    @GetMapping("/getUserByEmail")
    fun getUserByEmail(@RequestParam email: String): UserProjection {
        return projectionsService.getUserByEmail(email)
            ?: throw NoSuchElementException("User with email $email not found")
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
