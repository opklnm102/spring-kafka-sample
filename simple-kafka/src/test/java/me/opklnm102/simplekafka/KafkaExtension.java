package me.opklnm102.simplekafka;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

public class KafkaExtension implements BeforeAllCallback, AfterAllCallback {

    private KafkaContainer kafka;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.3.2"));
        kafka.start();

        System.setProperty("spring.kafka.bootstrap-servers", kafka.getBootstrapServers());  // for Kafka AdminClient
        System.setProperty("spring.kafka.producer.bootstrap-servers", kafka.getBootstrapServers());  // for Kafka producer
        System.setProperty("spring.kafka.consumer.bootstrap-servers", kafka.getBootstrapServers());  // for Kafka consumer
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        // do nothing, Testcontainers handles container shutdown
    }
}
