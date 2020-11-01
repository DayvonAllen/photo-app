package com.example.userservice.util;

import com.example.userservice.services.AlbumServiceClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


@Component
public class AlbumsFallbackFactory implements FallbackFactory<AlbumServiceClient> {
    @Override
    public AlbumServiceClient create(Throwable throwable) {
        return new AlbumsFallback(throwable);
    }
}
