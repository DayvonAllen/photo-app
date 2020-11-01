package com.example.albumservice.service;

import com.example.albumservice.api.v1.domain.AlbumDto;
import com.example.albumservice.api.v1.domain.AlbumDtoList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Override
    public AlbumDtoList getAlbums(Long id) {
        List<AlbumDto> albumDtoLists = new ArrayList<>();
        AlbumDto albumDto = new AlbumDto();
        albumDto.setAlbumsId(1L);
        albumDto.setUserId(id);
        albumDto.setName("song");
        albumDto.setDescription("description");
        AlbumDto albumDto1 = new AlbumDto();
        albumDto1.setAlbumsId(2L);
        albumDto1.setUserId(id);
        albumDto1.setName("New Song");
        albumDto1.setDescription("description2");
        albumDtoLists.add(albumDto);
        albumDtoLists.add(albumDto1);
        return new AlbumDtoList(albumDtoLists);
    }
}
