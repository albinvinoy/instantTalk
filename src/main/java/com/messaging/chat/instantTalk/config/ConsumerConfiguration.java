package com.messaging.chat.instantTalk.config;

import com.messaging.chat.instantTalk.model.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerPartitionAssignor;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.internals.PartitionAssignorAdapter;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Properties;

@Component
@Slf4j
@AllArgsConstructor
public class ConsumerConfiguration {

    @Autowired
    private final ConsumerEnvConfig consumerEnvConfig;


    @Bean
    public Properties consumerConfigs() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerEnvConfig.getBootstrapServer());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, Message.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "check-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        return props;
    }

    @Bean
    public KafkaConsumer<String, Message> kafkaConsumer() {
        KafkaConsumer<String, Message> kafkaConsumer = new KafkaConsumer<>(consumerConfigs());
        kafkaConsumer.subscribe(Arrays.asList("chatTopic"));


        return kafkaConsumer;
    }

}
