package com.bsep.smart.home.controller;


import com.bsep.smart.home.services.message.GetMessagesForProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {
    private final GetMessagesForProperty getMessagesForProperty;

    @GetMapping("/{propertyId}")
    public List<String> getMessages(@PathVariable String propertyId) {
        return getMessagesForProperty.execute(propertyId);
    }
}
