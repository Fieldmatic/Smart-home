package com.bsep.smart.home.services.message;


import com.bsep.smart.home.model.Log;
import com.bsep.smart.home.mongorepository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetMessagesForProperty {
    private final LogRepository logRepository;

    @Transactional
    public List<String> execute(String propertyId) {
        List<Log> logs = logRepository.getLogsByPropertyId(propertyId);
        return logs.stream().map(Log::getMessage).collect(Collectors.toList());
    }
}
