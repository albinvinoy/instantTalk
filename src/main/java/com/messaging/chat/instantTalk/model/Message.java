package com.messaging.chat.instantTalk.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializer<Message>, Deserializer<Message> {

    private String message;
    private Date date;
    private String userId;


    @Override
    public Message deserialize(String s, byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(bytes, Message.class);
        } catch (IOException e) {
            e.printStackTrace();
            return Message.builder().build();
        }

    }

    @Override
    public Message deserialize(String topic, Headers headers, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(data, Message.class);
        } catch (IOException e) {
            e.printStackTrace();
            return Message.builder().build();
        }

    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String s, Message message) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsBytes(message);
        } catch (JsonProcessingException e) {
            return new byte[0];
        }

    }

    @Override
    public byte[] serialize(String topic, Headers headers, Message data) {

        ObjectMapper objectMapper = new ObjectMapper();

        try{
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e){
            return new byte[0];
        }

    }

    @Override
    public void close() {

    }
}
