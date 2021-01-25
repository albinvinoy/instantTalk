package com.messaging.chat.instantTalk.controller;

import com.messaging.chat.instantTalk.model.Message;
import com.messaging.chat.instantTalk.streaming.MessageSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestControllerAdvice
@AllArgsConstructor
@Slf4j
@RequestMapping("/api")
public class ChatController {

    private final MessageSender messageSender;

    @PostMapping("/send")
    public void sendMessage(@RequestBody Message message){
        log.info("sending message");
        message.setDate(new Date());
        messageSender.sendMessage("chatTopic", message);
    }

}
