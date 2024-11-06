package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.TaskAggregate
import ru.quipy.logic.TaskAggregateCommands
import java.util.UUID

@RestController
@RequestMapping("/tasks")
class TaskController(private val taskCommands: TaskAggregateCommands) {

    @PostMapping
    fun createTask(
        @RequestParam name: String,
        @RequestParam description: String,
        @RequestParam executors: List<UUID>
    ): TaskAggregate {
        return taskCommands.createTask(name, description, executors)
    }
}
