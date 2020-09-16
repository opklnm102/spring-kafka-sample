package me.opklnm102.multikafkaproducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
public class KafkaEventPublisher implements EventPublisher {

    private final KafkaTemplate<String, BaseEvent<?>> kafkaTemplate;

    public KafkaEventPublisher(KafkaTemplate<String, BaseEvent<?>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(BaseEvent<?> event) {
        kafkaTemplate.send(event.getTopic(), event)
                     .addCallback(result -> log.info("send success - topic : {}, payload : {}, result : {}", event.getTopic(), event, result),
                             ex -> log.error("send fail - topic : {}, payload : {}", event.getTopic(), event, ex));
    }
}

