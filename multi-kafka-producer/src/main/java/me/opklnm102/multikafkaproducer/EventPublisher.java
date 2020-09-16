package me.opklnm102.multikafkaproducer;

import org.springframework.scheduling.annotation.Async;

public interface EventPublisher {

    @Async
    default void sendAsync(BaseEvent<?> event) {
        send(event);
    }

    void send(final BaseEvent<?> event);
}
