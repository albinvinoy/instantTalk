package com.messaging.chat.instantTalk.streaming;

import com.messaging.chat.instantTalk.config.ConsumerConfiguration;
import com.messaging.chat.instantTalk.config.ConsumerEnvConfig;
import com.messaging.chat.instantTalk.model.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

@Component
@Slf4j
@AllArgsConstructor
public class MessageReceiver implements ApplicationRunner {

    @Autowired
    private final ConsumerEnvConfig consumerEnvConfig;
    @Autowired
    private final ConsumerConfiguration consumerConfiguration;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Starting consumer to read");
//        try {
////            log.info("Env variable loaded {}", consumerEnvConfig.getTopicName());
//            consumerConfiguration.kafkaConsumer().subscribe(Arrays.asList(consumerEnvConfig.getTopicName()), new ConsumerRebalanceListener() {
//                @Override
//                public void onPartitionsRevoked(Collection<TopicPartition> collection) {
//                    log.info("Consumer revoked {} ", collection);
//                }
//
//                @Override
//                public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
//                    System.out.println("Assigned " + partitions);
//                    for (TopicPartition tp : partitions) {
//                        OffsetAndMetadata oam = consumerConfiguration.kafkaConsumer().committed(tp);
//                        if (oam != null) {
//                            System.out.println("Current offset is " + oam.offset());
//                        } else {
//                            System.out.println("No committed offsets");
//                        }
//
//                    }
//                }
//            });
//
//        } catch (
//                WakeupException e) {
//            log.error(e.getMessage());
//        }
//            log.info("Topics subscribed to {}", consumerConfiguration.kafkaConsumer().subscription());

        while (true) {
            try {
                ConsumerRecords<String, Message> records = consumerConfiguration.kafkaConsumer().poll(Duration.ofMillis(100));
                records.iterator().forEachRemaining(r ->
                        log.info("Polled from consumer {}", r.value()));
            } catch (Exception e) {
                log.error("Something happened ", e);
                break;
            }
        }

    }


}
