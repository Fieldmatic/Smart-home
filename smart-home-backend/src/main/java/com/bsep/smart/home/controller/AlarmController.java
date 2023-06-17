package com.bsep.smart.home.controller;

import com.bsep.smart.home.dto.request.users.PageRequest;
import com.bsep.smart.home.dto.response.AlarmResponse;
import com.bsep.smart.home.dto.response.PageResponse;
import com.bsep.smart.home.model.Permission;
import com.bsep.smart.home.security.HasAnyPermission;
import com.bsep.smart.home.services.alarm.GetAlarms;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/alarm")
@RequiredArgsConstructor
public class AlarmController {

    private final GetAlarms getAlarms;

    @GetMapping
    @HasAnyPermission({Permission.PROPERTY_MANIPULATION})
    public PageResponse<AlarmResponse> getAlarms(@Valid final PageRequest pageRequest) {
        return getAlarms.execute(pageRequest.getPageNumber(), pageRequest.getPageSize());
    }

    @GetMapping(value = "/test")
    public void unhandledExceptionTest() {
        throw new RuntimeException("This is a test.");
    }
}
