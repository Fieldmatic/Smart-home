package com.bsep.smart.home.services.statistics;

import com.bsep.smart.home.dto.response.AdminStatisticsResponse;
import com.bsep.smart.home.mongorepository.LogRepository;
import com.bsep.smart.home.repository.AlarmRepository;
import com.bsep.smart.home.repository.DeviceRepository;
import com.bsep.smart.home.repository.PersonRepository;
import com.bsep.smart.home.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GetAdminDashboardStatistics {
    private final PersonRepository personRepository;
    private final PropertyRepository propertyRepository;
    private final DeviceRepository deviceRepository;
    private final LogRepository logRepository;
    private final AlarmRepository alarmRepository;
    private final GetNumberOfLogsForPreviousYear getNumberOfLogsForPreviousYear;
    private final GetNumberOfMessagesForPreviousYear getNumberOfMessagesForPreviousYear;
    private final GetNumberOfAlarmsForPreviousYear getNumberOfAlarmsForPreviousYear;

    public AdminStatisticsResponse execute() {
        final Long usersCount = personRepository.count();
        final Long ownersCount = personRepository.countByRoleName("OWNER");
        final Long tenantsCount = personRepository.countByRoleName("TENANT");
        final Long propertiesCount = propertyRepository.count();
        final Long devicesCount = deviceRepository.count();
        final Long logsCount = logRepository.count();
        final Long messagesCount = logRepository.countLogsByProcessedIsTrue();
        final Long alarmsCount = alarmRepository.count();
        final Map<String, Long> logsCountInLastYearByMonths = getNumberOfLogsForPreviousYear.execute();
        final Map<String, Long> messagesCountInLastYearByMonths = getNumberOfMessagesForPreviousYear.execute();
        final Map<String, Long> alarmsCountInLastYearByMonths = getNumberOfAlarmsForPreviousYear.execute();
        return AdminStatisticsResponse.builder()
                .usersCount(usersCount)
                .alarmsCount(alarmsCount)
                .devicesCount(devicesCount)
                .logsCount(logsCount)
                .messagesCount(messagesCount)
                .ownersCount(ownersCount)
                .propertiesCount(propertiesCount)
                .tenantsCount(tenantsCount)
                .alarmsCountInLastYearByMonths(alarmsCountInLastYearByMonths)
                .logsCountInLastYearByMonths(logsCountInLastYearByMonths)
                .messagesCountInLastYearByMonths(messagesCountInLastYearByMonths)
                .build();
    }
}
