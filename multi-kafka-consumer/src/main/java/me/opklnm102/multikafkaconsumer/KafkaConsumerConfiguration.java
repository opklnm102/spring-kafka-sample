package me.opklnm102.multikafkaconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.MessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.HashMap;

@Configuration
@Slf4j
public class KafkaConsumerConfiguration {

    @Bean
    public ConsumerFactory<String, BaseEvent<?>> consumerFactory(KafkaProperties properties) {
        return new DefaultKafkaConsumerFactory<>(properties.buildConsumerProperties());
    }

    @Bean
    public RecordMessageConverter recordMessageConverter(ObjectMapper objectMapper) {
        return new StringJsonMessageConverter(objectMapper);
    }

    @Bean
    public KafkaListenerContainerFactoryBuilder factoryBuilder(KafkaProperties properties, RecordMessageConverter recordMessageConverter) {
        return new KafkaListenerContainerFactoryBuilder(properties, recordMessageConverter);
    }

    public static class KafkaListenerContainerFactoryBuilder {

        private final KafkaProperties kafkaProperties;
        private final MessageConverter messageConverter;

        public KafkaListenerContainerFactoryBuilder(KafkaProperties kafkaProperties, MessageConverter messageConverter) {
            this.kafkaProperties = kafkaProperties;
            this.messageConverter = messageConverter;
        }

        public ConcurrentKafkaListenerContainerFactory<String, BaseEvent<?>> build(String groupId) {
            return build(groupId, 3, 3000);
        }

        public <T> ConcurrentKafkaListenerContainerFactory<String, T> build(String groupId, int concurrency, long pollTimeout) {
            var properties = new HashMap<>(kafkaProperties.buildConsumerProperties());
            properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

            var factory = new ConcurrentKafkaListenerContainerFactory<String, T>();
            factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(properties));
            factory.setMessageConverter(messageConverter);
            factory.setConcurrency(concurrency);
            factory.getContainerProperties().setPollTimeout(pollTimeout);
            return factory;
        }

        public ConcurrentKafkaListenerContainerFactory<String, BaseEvent<?>> buildBatch(String groupId, int concurrency, long maxPollRecords) {
            var properties = new HashMap<>(kafkaProperties.buildConsumerProperties());
            properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
            properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);

            var factory = new ConcurrentKafkaListenerContainerFactory<String, BaseEvent<?>>();
            factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(properties));
            factory.setMessageConverter(messageConverter);
            factory.setConcurrency(concurrency);
            factory.setBatchListener(true);
            return factory;
        }
    }
}
