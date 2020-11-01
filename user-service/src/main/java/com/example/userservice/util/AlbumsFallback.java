package com.example.userservice.util;

import com.example.userservice.api.v1.domain.AlbumDtoList;
import com.example.userservice.services.AlbumServiceClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class AlbumsFallback implements AlbumServiceClient {

    private final Throwable cause;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AlbumsFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public AlbumDtoList getAlbums(Long id) {
        if(cause instanceof FeignException && ((FeignException) cause).status() == 404){
            logger.error("404 error took place while trying to execute the getAlbum method wit usedId: "
                    + id + ". Error Message: "
                    + cause.getLocalizedMessage());
        } else {
            logger.error("Other exception took place: " + cause.getLocalizedMessage());
        }
        return new AlbumDtoList(new ArrayList<>());
    }
}
