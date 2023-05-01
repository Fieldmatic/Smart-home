package com.bsep.smart.home.dto.request.users;

import com.bsep.smart.home.constants.PageConstants;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class PageRequest {
    @Min(1)
    @Max(PageConstants.MAX_PAGE_SIZE)
    private int pageSize = PageConstants.DEFAULT_PAGE_SIZE;

    @Min(0)
    private int pageNumber = PageConstants.DEFAULT_PAGE_NUMBER;
}