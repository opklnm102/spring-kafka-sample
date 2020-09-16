package me.opklnm102.multikafkaconsumer;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString(callSuper = true)
public class TestEventData {

    private final String name;
    private final int age;

    @JsonCreator
    public TestEventData(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
