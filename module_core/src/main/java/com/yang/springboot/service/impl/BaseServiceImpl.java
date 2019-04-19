package com.yang.springboot.service.impl;

import com.yang.springboot.common.utils.ModelConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yanghao
 * @date 2019-04-18 17:02
 */
@Service
@Slf4j
public class BaseServiceImpl {


    protected <T, S> T toDomain(S source, Class<T> target) {
        T t = ModelConvert.to(source, target);
        return toDomainCustomize(t, source, target);
    }

    protected <T, S> T toDto(S source, Class<T> target) {
        T dto = ModelConvert.to(source, target);
        return toDtoCustomize(dto, source, target);
    }

    protected <T, S> T toDomainCustomize(T domain, S source, Class<T> target) {
        return domain;
    }

    protected <T, S> T toDtoCustomize(T dto, S source, Class<T> target) {
        return dto;
    }


}
