package com.bsep.smart.home.controller;

import com.bsep.smart.home.model.Log;
import com.bsep.smart.home.services.logs.GetAllLogsForProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
public class LogController {
    private final GetAllLogsForProperty getAllLogsForProperty;

    @GetMapping("/{propertyId}")
    public List<Log> getAllLogsForProperty(@PathVariable String propertyId) {
        return getAllLogsForProperty.execute(propertyId);
    }


}
