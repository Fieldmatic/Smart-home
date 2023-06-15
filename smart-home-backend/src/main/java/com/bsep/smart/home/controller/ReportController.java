package com.bsep.smart.home.controller;

import com.bsep.smart.home.services.report.GenerateReportForPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    private final GenerateReportForPeriod generateReportForPeriod;

    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getReport(@NotBlank @RequestParam String start, @NotBlank @RequestParam String end) throws IOException {
        return generateReportForPeriod.execute(LocalDate.parse(start), LocalDate.parse(end));
    }
}
