package me.opklnm102.simplekafka;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Slf4j
public class KafkaAdminController {

    private final AdminClient adminClient;

    private Collection<String> topics;

    public KafkaAdminController(AdminClient adminClient) {
        this.adminClient = adminClient;
    }

    @SneakyThrows
    @GetMapping(path = "/topics")
    public Collection<String> readTopics() {
        topics = adminClient.listTopics()
                            .names()
                            .get();

        log.info("listTopics : {}", String.join(",", topics));

        return topics;
    }

    @DeleteMapping(path = "/topics")
    public void deleteTopics() {
        adminClient.deleteTopics(topics);
    }
}
