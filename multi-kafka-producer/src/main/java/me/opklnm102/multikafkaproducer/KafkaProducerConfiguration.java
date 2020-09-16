package me.opklnm102.multikafkaproducer;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class KafkaProducerConfiguration {

    @Bean
    public <T> ProducerFactory<String, BaseEvent<?>> stringProducerFactory(KafkaProperties properties) {
        var factory = new DefaultKafkaProducerFactory<String, BaseEvent<?>>(properties.buildProducerProperties());

        String transactionIdPrefix = properties.getProducer().getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }
        return factory;
    }

    @Bean
    public RecordMessageConverter recordMessageConverter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public <T> KafkaTemplate<String, BaseEvent<?>> kafkaTemplate(ProducerFactory<String, BaseEvent<?>> producerFactory,
                                                                 RecordMessageConverter recordMessageConverter) {
        var kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setMessageConverter(recordMessageConverter);
        return kafkaTemplate;
    }

    @Bean
    public EventPublisher eventPublisher(KafkaTemplate<String, BaseEvent<?>> kafkaTemplate) {
        return new KafkaEventPublisher(kafkaTemplate);
    }
}
