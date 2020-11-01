package com.example.albumservice.api.v1.mapper;

import com.example.albumservice.api.v1.domain.AlbumDto;
import com.example.albumservice.model.Album;

public interface AlbumMapper {
    AlbumDto albumToAlbumDtoMapper(Album album);
    Album albumDtoToAlbumMapper(AlbumDto albumDto);
}
