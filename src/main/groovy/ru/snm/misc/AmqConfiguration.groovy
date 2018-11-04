package ru.snm.misc


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.config.JmsListenerContainerFactory
import org.springframework.jms.support.converter.MappingJackson2MessageConverter
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.jms.support.converter.MessageType

/**
 *
 * @author sine-loco
 */
@EnableJms
@Configuration
class AmqConfiguration {

    @Bean
    JmsListenerContainerFactory jmsListenerFactory() {
        def factory = new DefaultJmsListenerContainerFactory()
        factory.messageConverter = messageConverter()
        factory
    }

    @Bean
    MessageConverter messageConverter() {
        def converter = new MappingJackson2MessageConverter()
        converter.targetType = MessageType.TEXT
        converter.typeIdPropertyName = '_type'
        converter
    }
}
