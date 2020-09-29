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
    private final String email;
    private final int age;
    
    @JsonCreator
    public TestEventData(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
