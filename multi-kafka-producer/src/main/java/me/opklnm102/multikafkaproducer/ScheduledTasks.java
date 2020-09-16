package me.opklnm102.multikafkaproducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTasks {

    private final EventPublisher eventPublisher;

    public ScheduledTasks(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void send(BaseEvent<?> payload) {
        eventPublisher.send(payload);
    }

    @Scheduled(fixedRate = 3000)
    public void reportCurrentTime() {
        send(new TestEvent(new TestEventData("mike", 15)));

        send(new ThingEvent(new ThingEventData(1)));
    }
}
