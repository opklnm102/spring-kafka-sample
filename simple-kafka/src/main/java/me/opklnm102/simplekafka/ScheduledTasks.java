package me.opklnm102.simplekafka;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Profile({"schedule"})
public class ScheduledTasks {

    private final KafkaProducer kafkaProducer;

    public ScheduledTasks(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Scheduled(fixedRate = 1000)
    public void reportCurrentTime() {
        kafkaProducer.send("test", String.format("hello %s", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
    }
}
