package com.example.wishslackapp.client.kafka;

import com.example.wishslackapp.utility.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {
    @Value("${topic.name.producer}")
    String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final JsonConverter jsonConverter;

    public <T> void publish(KafkaMessage<T> kafkaMessage) {
        String message = jsonConverter.objectToJson(kafkaMessage.getBody());

        kafkaTemplate.send(topicName, message);
    }
}
