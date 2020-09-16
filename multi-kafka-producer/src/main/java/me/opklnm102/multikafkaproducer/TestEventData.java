package me.opklnm102.multikafkaproducer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString(callSuper = true)
public class TestEventData {

    private String name;
    private int age;

    public TestEventData(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
