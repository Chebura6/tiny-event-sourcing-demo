package ru.quipy.logic

import org.springframework.stereotype.Component
import ru.quipy.api.TaskAggregate
import ru.quipy.api.TaskStatus
import java.util.UUID

@Component
class TaskAggregateCommandsImpl : TaskAggregateCommands {

    override fun createTask(name: String, description: String, executors: List<UUID>): TaskAggregate {
        return TaskAggregate(
            id = UUID.randomUUID(),
            name = name,
            description = description,
            status = TaskStatus.CREATED,
            executors = executors
        )
    }

    override fun updateTask(id: UUID, name: String?, description: String?, status: TaskStatus) {
        TODO("Not yet implemented")
    }

    override fun assignExecutors(taskId: UUID, executors: List<UUID>) {
        TODO("Not yet implemented")
    }

}
