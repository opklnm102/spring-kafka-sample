package me.opklnm102.multikafkaconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestKafkaListener {

    @KafkaListener(topics = "test")
    public void receiveTestTopics(
            @Payload TestEvent message,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        log.info("Receiver on test topic - partition : {}, payload : {}", partition, message);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BaseEvent<?>> testContainerFactory(KafkaConsumerConfiguration.KafkaListenerContainerFactoryBuilder factoryBuilder) {
        return factoryBuilder.build("test", 3, 3000);
    }
}
