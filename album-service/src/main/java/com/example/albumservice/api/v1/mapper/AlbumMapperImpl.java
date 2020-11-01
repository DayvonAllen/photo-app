package com.example.albumservice.api.v1.mapper;

import com.example.albumservice.api.v1.domain.AlbumDto;
import com.example.albumservice.model.Album;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapperImpl implements AlbumMapper {
    @Override
    public AlbumDto albumToAlbumDtoMapper(Album album) {
        if(album == null){
            return null;
        }
        return new AlbumDto(album.getId(), album.getUserId(), album.getName(), album.getDescription());
    }

    @Override
    public Album albumDtoToAlbumMapper(AlbumDto albumDto) {
        if(albumDto == null){
            return null;
        }
        return new Album(albumDto.getAlbumsId(), albumDto.getUserId(), albumDto.getName(), albumDto.getDescription());
    }
}
