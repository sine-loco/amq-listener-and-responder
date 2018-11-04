package ru.snm.misc


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification
import spock.lang.Stepwise
import spock.lang.Subject

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

/**
 *
 * @author sine-loco
 */
@Stepwise
@SpringBootTest( webEnvironment = NONE )
@TestPropertySource( 'classpath:application-aqm.properties' )
class AmqQueueNamesSpec extends Specification {
    @Subject
    @Autowired
    AmqQueueNames amqQueueNames

    Properties expectedProps

    def setup() {
        expectedProps = new Properties()
        expectedProps.load(
                AmqQueueNamesSpec.getResourceAsStream( '/application-aqm.properties' ) )
    }

    def "ensures that configurationProperties are loaded in AppContext"() {
        expect:
        amqQueueNames
    }

    def "ensures that configurationProperties are read correctly"() {
        given:
        def actualQns = amqQueueNames.properties
        //actualQns.removeAll { k, v -> k == 'class' }
        //actualQns.removeAll { k, v -> k == 'first' }
        ConfigObject expectedQns = new ConfigSlurper().parse( expectedProps ).queues

        expect: 'all queue groups are read'
        expectedQns.entrySet().each { expectedQueueGroup ->
            String expectedQueueGroupName = expectedQueueGroup.key
            def actualQueueGroup = actualQns.get expectedQueueGroupName
            assert actualQueueGroup
            //and:
            assert expectedQueueGroup.value // TODO
        }




    }

}

/*
public class YamlFileApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {
    try {
        Resource resource = applicationContext.getResource("classpath:file.yml");
        YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
        PropertySource<?> yamlTestProperties = sourceLoader.load("yamlTestProperties", resource, null);
        applicationContext.getEnvironment().getPropertySources().addFirst(yamlTestProperties);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
  }
}


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class, initializers = YamlFileApplicationContextInitializer.class)
public class SimpleTest {
  @Test
  public test(){
    // test your properties
  }
}
 */
