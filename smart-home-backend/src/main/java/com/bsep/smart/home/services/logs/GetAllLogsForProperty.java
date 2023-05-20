package com.bsep.smart.home.services.logs;

import com.bsep.smart.home.model.Log;
import com.bsep.smart.home.mongorepository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllLogsForProperty {
    private final LogRepository logRepository;

    @Transactional
    public List<Log> execute(String propertyId) {
        return logRepository.getLogsByPropertyId(propertyId);
    }
}
