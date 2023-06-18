package com.bsep.smart.home.services.statistics;

import com.bsep.smart.home.mongorepository.LogRepository;
import com.bsep.smart.home.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GetNumberOfAlarmsForPreviousYear {
    private final AlarmRepository alarmRepository;

    public Map<String, Long> execute() {
        YearMonth currentYearMonth = YearMonth.from(LocalDateTime.now());
        Map<String, Long> alarmsCountByMonths = new HashMap<>();

        for (int i = 0; i < 12; i++) {
            YearMonth yearMonth = currentYearMonth.minus(i, ChronoUnit.MONTHS);
            LocalDateTime start = LocalDateTime.of(yearMonth.getYear(), yearMonth.getMonth(), 1, 0, 0, 0);
            LocalDateTime end = LocalDateTime.of(yearMonth.getYear(), yearMonth.getMonth(), yearMonth.lengthOfMonth(), 23, 59, 59);
            alarmsCountByMonths.put(yearMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()), alarmRepository.countAlarmsByTimeBetween(start, end));
        }

        return alarmsCountByMonths;
    }
}
