package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class Repeater {

    @Value("${local.message}")
    String localMessage;
    
    @Value("${global.message}")
    String globalMessage;

    @Value("${message}")
    String message;

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageDTO sayMessage() {
        return new MessageDTO(message, globalMessage, localMessage);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class MessageDTO {
        private String message;
        private String globalMessage;
        private String localMessage;
    }
}