package com.example.userservice.util;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Exception decode(String s, Response response) {
        logger.error(response.reason());
        switch (response.status()){
            case 400:
                break;
            case 404:
                return new ResponseStatusException(HttpStatus.valueOf(response.status()), response.reason());
            default:
                return new Exception(response.reason());
        }
        return null;
    }
}
