package com.bsep.smart.home.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class AdminStatisticsResponse {
    Long usersCount;
    Long ownersCount;
    Long tenantsCount;
    Long propertiesCount;
    Long devicesCount;
    Long logsCount;
    Map<String, Long> logsCountInLastYearByMonths;
    Long messagesCount;
    Map<String, Long> messagesCountInLastYearByMonths;
    Long alarmsCount;
    Map<String, Long> alarmsCountInLastYearByMonths;
}
