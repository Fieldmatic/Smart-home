package com.bsep.smart.home.controller;


import com.bsep.smart.home.dto.request.users.PageRequest;
import com.bsep.smart.home.dto.response.PageResponse;
import com.bsep.smart.home.services.message.GetMessagesForProperty;
import com.bsep.smart.home.services.message.SearchMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {
    private final GetMessagesForProperty getMessagesForProperty;
    private final SearchMessages searchMessages;

    @GetMapping("/{propertyId}")
    public PageResponse<String> getMessagesForProperty(@PathVariable String propertyId, @Valid final PageRequest pageRequest) {
        return getMessagesForProperty.execute(propertyId, pageRequest.getPageNumber(), pageRequest.getPageSize());
    }

    @GetMapping("/filter/{propertyId}")
    public PageResponse<String> getMessages(@Valid final PageRequest pageRequest,
                                            @RequestParam(required = false) String filter,
                                            @PathVariable String propertyId) {
        return searchMessages.execute(propertyId, filter, pageRequest.getPageNumber(), pageRequest.getPageSize());
    }
}
