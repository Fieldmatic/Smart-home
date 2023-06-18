package com.bsep.smart.home.controller;

import com.bsep.smart.home.dto.response.AdminStatisticsResponse;
import com.bsep.smart.home.model.Permission;
import com.bsep.smart.home.security.HasAnyPermission;
import com.bsep.smart.home.services.statistics.GetAdminDashboardStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {
    private final GetAdminDashboardStatistics getAdminDashboardStatistics;

    @GetMapping("/admin")
    @HasAnyPermission({Permission.READ_ADMIN_STATISTICS})
    public AdminStatisticsResponse getAdminDashboard() {
        return getAdminDashboardStatistics.execute();
    }
}
