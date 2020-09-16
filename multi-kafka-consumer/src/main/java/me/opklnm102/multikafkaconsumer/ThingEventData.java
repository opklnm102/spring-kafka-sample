package me.opklnm102.multikafkaconsumer;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString(callSuper = true)
public class ThingEventData {

    private final int age;

    @JsonCreator
    public ThingEventData(int age) {
        this.age = age;
    }
}
