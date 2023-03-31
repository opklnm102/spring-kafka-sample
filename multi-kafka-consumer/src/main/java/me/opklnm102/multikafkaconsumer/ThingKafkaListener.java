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
public class ThingKafkaListener {

    @KafkaListener(topics = "thing1", containerFactory = "thingContainerFactory")
    public void receiveThingTopics(
            @Payload ThingEvent message,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        log.info("Receiver on thing1 topic - partition : {}, payload : {}", partition, message);
    }
}
