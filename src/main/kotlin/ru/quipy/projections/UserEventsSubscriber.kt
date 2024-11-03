package ru.quipy.projections

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import ru.quipy.api.UserRegisteredEvent
import ru.quipy.api.UserLoginAttemptedEvent
import ru.quipy.core.annotations.EventSubscriber
import ru.quipy.core.annotations.SubscribeEvent

@Component
@EventSubscriber
class UserEventsSubscriber {

    private val logger = LoggerFactory.getLogger(UserEventsSubscriber::class.java)

    @SubscribeEvent
    fun onUserRegistered(event: UserRegisteredEvent) {
        logger.info("UserRegisteredEvent received: User ID=${event.id}, Login=${event.login}")
    }

    @SubscribeEvent
    fun onUserLoginAttempted(event: UserLoginAttemptedEvent) {
        logger.info("UserLoginAttemptedEvent received: Login=${event.login}, Success=${event.success}")
    }
}
