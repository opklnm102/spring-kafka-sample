package me.opklnm102.multikafkaconsumer;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;

public class ThingEvent extends BaseEvent<ThingEventData> {

    @JsonCreator
    public ThingEvent(String version, String sender, LocalDateTime timestamp, ThingEventData data) {
        super(version, sender, timestamp, data);
    }

    public ThingEvent(ThingEventData data) {
        super(data);
    }

    @Override
    public String getTopic() {
        return "thing1";
    }
}
