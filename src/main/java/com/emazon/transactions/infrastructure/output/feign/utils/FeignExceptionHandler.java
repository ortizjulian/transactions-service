package com.emazon.transactions.infrastructure.output.feign.utils;

import com.emazon.transactions.domain.model.StatusCodeEnum;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class FeignExceptionHandler {

    public static StatusCodeEnum handleFeignException(FeignException e) {
        HttpStatusCode status = HttpStatusCode.valueOf(e.status());

        if (status == HttpStatus.NOT_FOUND) {
            return StatusCodeEnum.NOT_FOUND;
        } else if (status == HttpStatus.BAD_REQUEST) {
            return StatusCodeEnum.BAD_REQUEST;
        } else if (status == HttpStatus.FORBIDDEN) {
            return StatusCodeEnum.FORBIDDEN;
        } else {
            return StatusCodeEnum.INTERNAL_SERVER_ERROR;
        }
    }
}
