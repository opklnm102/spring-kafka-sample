package me.opklnm102.multikafkaconsumer;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;

public class TestEvent extends BaseEvent<TestEventData> {

    @JsonCreator
    public TestEvent(String version, String sender, LocalDateTime timestamp, TestEventData data) {
        super(version, sender, timestamp, data);
    }

    public TestEvent(TestEventData data) {
        super(data);
    }

    @Override
    public String getTopic() {
        return "test";
    }
}
