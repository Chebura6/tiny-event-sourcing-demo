package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.TaskAggregate
import ru.quipy.api.TaskStatus
import ru.quipy.logic.TaskAggregateCommands
import ru.quipy.repository.ProjectProjectionRepository
import ru.quipy.repository.StatusProjectionRepository
import ru.quipy.repository.TaskProjectionRepository
import ru.quipy.service.ProjectionsService
import java.util.UUID

@RestController
@RequestMapping("/tasks")
class TaskController(
    private val taskCommands: TaskAggregateCommands,
    val projectionsService: ProjectionsService,
    val taskProjectionRepository: TaskProjectionRepository,
    val statusProjectionRepository: StatusProjectionRepository,
    val projectProjectionRepository: ProjectProjectionRepository
) {

    @PostMapping
    fun createTask(
        @RequestParam name: String,
        @RequestParam description: String,
        @RequestParam executors: List<UUID>
    ): TaskAggregate {
        return taskCommands.createTask(name, description, executors)
    }

    @PutMapping("/{id}")
    fun updateTask(
        @PathVariable id: UUID,
        @RequestParam name: String?,
        @RequestParam description: String?,
        @RequestParam status: TaskStatus
    ) {
        taskCommands.updateTask(id, name, description, status)
    }

    @PutMapping("/{id}/assign")
    fun assignExecutors(
        @PathVariable id: UUID,
        @RequestParam executors: List<UUID>
    ) {
        taskCommands.assignExecutors(id, executors)
    }
}
