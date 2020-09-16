package me.opklnm102.simplekafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
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
        kafkaTemplate.send(topic, payload);
        log.info("Message: " + payload + " sent to topic: " + topic);
    }

    @Scheduled(fixedRate = 1000)
    public void reportCurrentTime() {
        send("test", String.format("hello %s", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
    }

    @KafkaListener(topics = "test")
    public void receiveTestTopics(ConsumerRecord<String, String> consumerRecord) {
        log.info("Receiver on topic : " + consumerRecord.toString());
    }
}
