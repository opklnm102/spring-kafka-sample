package me.opklnm102.simplekafkaproducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class ScheduledTasks {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ScheduledTasks(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, String payload) {
        kafkaTemplate.send(topic, payload)
                     .addCallback(result -> log.info("send success - topic : {}, payload : {}, result : {}", topic, payload, result),
                             ex -> log.error("send fail - topic : {}, payload : {}", topic, payload, ex));
    }

    @Scheduled(fixedRate = 3000)
    public void reportCurrentTime() {
        send("test", String.format("hello %s", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
    }
}
