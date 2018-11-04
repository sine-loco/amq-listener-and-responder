package ru.snm.misc

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties

/**
 *
 * @author sine-loco
 */
@SpringBootApplication
@EnableConfigurationProperties
class AmqListenerAndResponder implements CommandLineRunner {
    private final static Logger logger = LogManager.getLogger()

    static void main( String[] args ) {
        SpringApplication.run AmqListenerAndResponder, args
    }

    @Override
    void run( String... args ) throws Exception {
        logger.info 'command line args: {}', args
    }
}
