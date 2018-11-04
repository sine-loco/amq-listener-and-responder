package ru.snm.misc

import org.apache.activemq.command.ActiveMQQueue
import org.spockframework.spring.SpringSpy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.core.MessageCreator
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.util.concurrent.BlockingVariable
import spock.util.concurrent.PollingConditions

import javax.jms.JMSException
import javax.jms.Message
import javax.jms.Queue
import javax.jms.Session

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

/**
 *
 * @author sine-loco
 */
@SpringBootTest( webEnvironment = NONE )
class FirstJmsListenerSpec extends Specification {

    @Autowired
    JmsTemplate jmsTemplate

    @Autowired
    AmqQueueNames queueNames

    @Subject
    @SpringSpy
    FirstJmsListener listener

    Queue resQueue
    Queue errQueue

    def setup() {
        resQueue = new ActiveMQQueue( queueNames.first.res )
        errQueue = new ActiveMQQueue( queueNames.first.err )
    }

    def "test onMessage"() {
        setup:
        def result = new BlockingVariable<Boolean>(0.2) // 200ms
        listener.onMessage( _ ) >> {
            result.set true
        }

        when:
        jmsTemplate.send( queueNames.first.req, { Session session ->
            def msg = session.createTextMessage()
            msg.text = '{ "name": "value" }'
            session.createConsumer( resQueue )
            msg.setJMSReplyTo( resQueue )
            msg
        } )

        then:
        //conditions.eventually {
            1 * listener.onMessage( _ )
        //}
        
        expect:
        result.get()
    }
}
