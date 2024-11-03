package ru.quipy.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.quipy.api.ProjectAggregate
import ru.quipy.api.TaskAggregate
import ru.quipy.api.UserAggregate
import ru.quipy.core.EventSourcingServiceFactory
import ru.quipy.logic.ProjectAggregateState
import ru.quipy.projections.AnnotationBasedProjectEventsSubscriber
import ru.quipy.projections.AnnotationBasedTaskEventsSubscriber
import ru.quipy.projections.AnnotationBasedUserEventsSubscriber
import ru.quipy.streams.AggregateEventStreamManager
import ru.quipy.streams.AggregateSubscriptionsManager
import java.util.*
import javax.annotation.PostConstruct

@Configuration
class EventSourcingLibConfiguration {

    private val logger = LoggerFactory.getLogger(EventSourcingLibConfiguration::class.java)

    @Autowired
    private lateinit var subscriptionsManager: AggregateSubscriptionsManager

    @Autowired
    private lateinit var projectEventSubscriber: AnnotationBasedProjectEventsSubscriber

    @Autowired
    private lateinit var userEventSubscriber: AnnotationBasedUserEventsSubscriber

    @Autowired
    private lateinit var taskEventSubscriber: AnnotationBasedTaskEventsSubscriber

    @Autowired
    private lateinit var eventSourcingServiceFactory: EventSourcingServiceFactory

    @Autowired
    private lateinit var eventStreamManager: AggregateEventStreamManager

    /**
     * Use this object to create/update the aggregate
     */
    @Bean
    fun projectEsService() = eventSourcingServiceFactory.create<UUID, ProjectAggregate, ProjectAggregateState>()

    @PostConstruct
    fun init() {
        // Demonstrates how to explicitly subscribe the instance of annotation based subscriber to some stream. See the [AggregateSubscriptionsManager]
        subscriptionsManager.subscribe<ProjectAggregate>(projectEventSubscriber)

        subscriptionsManager.subscribe<TaskAggregate>(taskEventSubscriber)

        subscriptionsManager.subscribe<UserAggregate>(userEventSubscriber)

        // Demonstrates how you can set up the listeners to the event stream
        eventStreamManager.maintenance {
            onRecordHandledSuccessfully { streamName, eventName ->
                logger.info("Stream $streamName successfully processed record of $eventName")
            }

            onBatchRead { streamName, batchSize ->
                logger.info("Stream $streamName read batch size: $batchSize")
            }
        }
    }

}