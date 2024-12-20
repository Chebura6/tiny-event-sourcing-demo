package ru.quipy.projections

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import ru.quipy.api.*
import ru.quipy.streams.annotation.AggregateSubscriber
import ru.quipy.streams.annotation.SubscribeEvent

@Component
@AggregateSubscriber(
    aggregateClass = TaskAggregate::class, subscriberName = "demo-subs-stream-1"
)
class AnnotationBasedTaskEventsSubscriber {

    private val logger: Logger = LoggerFactory.getLogger(AnnotationBasedTaskEventsSubscriber::class.java)

    @SubscribeEvent
    fun onNewTaskCreated(event: NewTaskCreatedEvent) {
        logger.info("New task created: {}", event.name)
    }

    @SubscribeEvent
    fun onTaskUpdated(event: TaskUpdatedEvent) {
        logger.info("Task updated event: {}", event.name)
    }

    @SubscribeEvent
    fun onExecutorsAssigned(event: ExecutorsAssignedEvent) {
        logger.info("Executors assigned events: {}", event)
    }
}
