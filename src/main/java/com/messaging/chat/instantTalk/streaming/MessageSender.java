package com.messaging.chat.instantTalk.streaming;

import com.messaging.chat.instantTalk.config.ConsumerEnvConfig;
import com.messaging.chat.instantTalk.config.ProducerConfiguration;
import com.messaging.chat.instantTalk.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

// this is to send message to kafka

@Component
@Data
@Slf4j
@AllArgsConstructor
public class MessageSender {

    private final ProducerConfiguration producerConfiguration;
    private final ConsumerEnvConfig consumerEnvConfig;

    public void sendMessage(String topic, Message message) {
        ProducerRecord<String, Message> producerRecord
                = new ProducerRecord<>(consumerEnvConfig.getTopicName(), message);
        producerConfiguration.kafkaProducer().send(producerRecord, (recordMetadata, e) -> {
            if (e != null) {
                log.error("Exception occurred when sending message to topic {} with error {}", recordMetadata.topic(), e);
            }
        });
    }

}
