package me.opklnm102.simplekafkaconsumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * total 4개의 consumer가 생성된다
 * receiveTestTopics - 1
 * receiveConcurrencyTestTopics - 3
 */
@Component
@Slf4j
public class TestKafkaListener {

    @KafkaListener(topics = "test")
    public void receiveTestTopics(ConsumerRecord<String, String> consumerRecord) {
        log.info("Receiver on test topic: " + consumerRecord.toString());
    }

    @KafkaListener(topics = "thing1", concurrency = "3")
    public void receiveConcurrencyTestTopics(ConsumerRecord<String, String> consumerRecord) {
        log.info("Receiver on test topic concurrency : " + consumerRecord.toString());
    }

    @KafkaListener(topics = "thing2")
    public void receiveTestTopics(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info("Receiver on test topic - partition : {}, payload : {}", partition, message);
    }
}
