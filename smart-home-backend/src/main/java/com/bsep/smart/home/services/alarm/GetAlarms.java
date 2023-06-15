package com.bsep.smart.home.services.alarm;

import com.bsep.smart.home.converter.AlarmConverter;
import com.bsep.smart.home.dto.response.AlarmResponse;
import com.bsep.smart.home.dto.response.PageResponse;
import com.bsep.smart.home.jpaspecification.PagingUtil;
import com.bsep.smart.home.model.facts.Alarm;
import com.bsep.smart.home.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetAlarms {
    private final AlarmRepository alarmRepository;

    public PageResponse<AlarmResponse> execute(int pageNumber, int pageSize) {

        final Pageable pageable = PagingUtil.getPageable(pageNumber, pageSize, Optional.of(Sort.by(Sort.Direction.DESC, "createdAt")));
        final Page<Alarm> alarmPage = alarmRepository.findByDeviceIdIsNull(pageable);

        final List<AlarmResponse> alarmResponses = alarmPage.getContent().stream().map(AlarmConverter::toAlarmResponse).toList();

        return PageResponse.<AlarmResponse>builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .items(alarmResponses)
                .totalPages(alarmPage.getTotalPages())
                .numberOfElements(alarmPage.getNumberOfElements())
                .totalElements(alarmPage.getTotalElements())
                .build();
    }
}
