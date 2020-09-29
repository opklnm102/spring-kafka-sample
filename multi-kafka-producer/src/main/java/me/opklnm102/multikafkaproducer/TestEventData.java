package me.opklnm102.multikafkaproducer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString(callSuper = true)
public class TestEventData {

    private String name;
    private String email;
    private int age;

    public TestEventData(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
