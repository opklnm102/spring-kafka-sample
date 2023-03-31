package me.opklnm102.multikafkaconsumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@Configuration
public class ThingKafkaConfiguration {
    
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ThingEvent> thingContainerFactory(KafkaConsumerConfiguration.KafkaListenerContainerFactoryBuilder factoryBuilder) {
        return factoryBuilder.build("thing1", 3, 3000);
    }
}
