package me.opklnm102.simplekafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

/**
 * KafkaAdmin + NewTopic으로 broker에 topic을 자동으로 생성
 * KafkaAdmin - Spring Boot Auto Configuration으로 생성됨
 */
@Configuration
public class KafkaAdminConfiguration {

    @Bean
    public NewTopic thing1() {
        return TopicBuilder.name("thing1")
                           .partitions(3)
                           .replicas(1)
                           .compact()
                           .build();
    }

    @Bean
    public NewTopic thing2() {
        return TopicBuilder.name("thing2")
                           .partitions(3)
                           .replicas(1)
                           .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
                           .build();
    }

    @Bean
    public AdminClient adminClient(KafkaAdmin kafkaAdmin) {
        return AdminClient.create(kafkaAdmin.getConfigurationProperties());
    }
}
