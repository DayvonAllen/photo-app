package com.example.albumservice.service;

import com.example.albumservice.api.v1.domain.AlbumDtoList;



public interface AlbumService {
   AlbumDtoList getAlbums(Long id);
}
