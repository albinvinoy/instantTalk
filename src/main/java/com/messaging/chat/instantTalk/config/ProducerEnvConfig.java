package com.messaging.chat.instantTalk.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@Getter
public class ProducerEnvConfig {

    @Value("${producer.bootstrap-servers}")
    private String bootstrapServer;




}
