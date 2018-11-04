package ru.snm.misc

import org.apache.activemq.command.ActiveMQTextMessage
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.jms.annotation.JmsListener
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Service



/**
 *
 * @author sine-loco
 */
@Service
class FirstJmsListener {
    private final static Logger logger = LogManager.getLogger()

    final JmsTemplate jmsTemplate

    FirstJmsListener( JmsTemplate jmsTemplate ) {
        this.jmsTemplate = jmsTemplate
    }

    /**
     * Listens to first group request queue
     * @param message
     */
    @JmsListener( destination = '#{amqQueueNames.first.req}' )
    void onMessage( ActiveMQTextMessage message ) {
        logger.debug 'received: {}', message



    }
}
