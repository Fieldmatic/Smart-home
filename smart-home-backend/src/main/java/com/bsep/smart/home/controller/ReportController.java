package com.bsep.smart.home.controller;

import com.bsep.smart.home.model.Permission;
import com.bsep.smart.home.security.HasAnyPermission;
import com.bsep.smart.home.services.report.GenerateReportForPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    private final GenerateReportForPeriod generateReportForPeriod;

    @GetMapping(value = "/{propertyId}", produces = MediaType.APPLICATION_PDF_VALUE)
    @HasAnyPermission({Permission.VIEW_MESSAGES})
    public byte[] getReport(@PathVariable UUID propertyId, @NotBlank @RequestParam String start, @NotBlank @RequestParam String end) throws IOException {
        return generateReportForPeriod.execute(propertyId, LocalDate.parse(start), LocalDate.parse(end));
    }
}
