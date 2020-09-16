package me.opklnm102.multikafkaproducer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString(callSuper = true)
public class ThingEventData {

    private final int age;

    public ThingEventData(int age) {
        this.age = age;
    }
}
