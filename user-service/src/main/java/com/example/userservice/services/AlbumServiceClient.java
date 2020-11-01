package com.example.userservice.services;

import com.example.userservice.api.v1.domain.AlbumDtoList;
import com.example.userservice.util.AlbumsFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ALBUMS-WS", fallbackFactory = AlbumsFallbackFactory.class)
public interface AlbumServiceClient {

    @GetMapping(value = "api/v1/albums/{id}")
    AlbumDtoList getAlbums(@PathVariable Long id);
}
