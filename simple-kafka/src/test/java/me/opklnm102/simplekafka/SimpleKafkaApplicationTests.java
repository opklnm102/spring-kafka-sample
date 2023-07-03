package me.opklnm102.simplekafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@ExtendWith(KafkaExtension.class)
@DirtiesContext
class SimpleKafkaApplicationTests {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaConsumer kafkaConsumer;

    @Test
    void contextLoads() {
    }

    @Test
    void test() throws Exception {
        // given
        var data = "sending test message ";

        // when
        kafkaProducer.send("test", data + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // then
        var messageConsumed = kafkaConsumer.getLatch().await(20, TimeUnit.SECONDS);

        assertThat(messageConsumed).isTrue();
        assertThat(kafkaConsumer.getPayload()).contains(data);
    }
}
