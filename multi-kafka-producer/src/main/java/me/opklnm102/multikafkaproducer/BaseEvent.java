package me.opklnm102.multikafkaproducer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Getter
@ToString
public abstract class BaseEvent<T> {

    private final String version;
    private final String sender;
    private final LocalDateTime timestamp;
    private final T data;

    @JsonIgnore
    public abstract String getTopic();

    protected BaseEvent(T data) {
        Assert.notNull(data, "data must not be null");

        this.version = "1.0";
        this.sender = generateSender();
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

    private String generateSender() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "unknown";
        }
    }
}
