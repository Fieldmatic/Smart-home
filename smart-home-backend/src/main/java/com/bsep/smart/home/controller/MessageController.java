package com.bsep.smart.home.controller;


import com.bsep.smart.home.dto.request.users.PageRequest;
import com.bsep.smart.home.dto.response.PageResponse;
import com.bsep.smart.home.services.message.GetMessagesForProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {
    private final GetMessagesForProperty getMessagesForProperty;

    @GetMapping("/{propertyId}")
    public PageResponse<String> getMessages(@PathVariable String propertyId, @Valid final PageRequest pageRequest) {
        return getMessagesForProperty.execute(propertyId, pageRequest.getPageNumber(), pageRequest.getPageSize());
    }
}
