package me.opklnm102.multikafkaproducer;

public class ThingEvent extends BaseEvent<ThingEventData> {

    protected ThingEvent(ThingEventData data) {
        super(data);
    }

    @Override
    public String getTopic() {
        return "thing1";
    }
}
