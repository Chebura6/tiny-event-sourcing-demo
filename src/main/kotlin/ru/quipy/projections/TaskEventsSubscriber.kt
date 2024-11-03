package ru.quipy.projections

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import ru.quipy.api.NewTaskCreatedEvent
import ru.quipy.api.TaskUpdatedEvent
import ru.quipy.api.ExecutorsAssignedEvent
import ru.quipy.core.EventSubscriber
import ru.quipy.core.annotations.SubscribeEvent
import ru.quipy.streams.annotation.SubscribeEvent

@Component
@EventSubscriber
class TaskEventsSubscriber {

    private val logger = LoggerFactory.getLogger(TaskEventsSubscriber::class.java)

    @SubscribeEvent
    fun onTaskCreated(event: NewTaskCreatedEvent) {
        logger.info("NewTaskCreatedEvent received: Task ID=${event.id}, Name=${event.name}, Description=${event.description}")
    }

    @SubscribeEvent
    fun onTaskUpdated(event: TaskUpdatedEvent) {
        logger.info("TaskUpdatedEvent received: Task ID=${event.id}, Name=${event.name}, Description=${event.description}, Status=${event.status}")
    }

    @SubscribeEvent
    fun onExecutorsAssigned(event: ExecutorsAssignedEvent) {
        logger.info("ExecutorsAssignedEvent received: Task ID=${event.taskId}, Executors=${event.executors}")
    }
}
