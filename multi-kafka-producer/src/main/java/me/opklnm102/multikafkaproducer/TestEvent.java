package me.opklnm102.multikafkaproducer;

public class TestEvent extends BaseEvent<TestEventData> {
    
    public TestEvent(TestEventData data) {
        super(data);
    }

    @Override
    public String getTopic() {
        return "test";
    }
}
