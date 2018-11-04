package ru.snm.misc

import groovy.transform.Canonical
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 *
 * @author sine-loco
 */
@Canonical
@Component
@ConfigurationProperties( 'queues' )
class AmqQueueNames {

    First first = new First()

    Second second = new Second()

    static class First {
        String req
        String res
        String err
    }

    static class Second {
        String req
        String res
        String err
    }
}
