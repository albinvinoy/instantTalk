package com.messaging.chat.instantTalk.config;

import com.messaging.chat.instantTalk.model.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Slf4j
@AllArgsConstructor
public class ProducerConfiguration {

    @Autowired
    private final ProducerEnvConfig producerEnvConfig;

    @Bean
    public Properties producerConfigs() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                producerEnvConfig.getBootstrapServer());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                Message.class);
        return props;
    }

    @Bean
    public KafkaProducer<String, Message> kafkaProducer(){
        return new KafkaProducer<>(producerConfigs());
    }

}
