package com.example.userservice.api.v1.domain;

import java.util.List;

public class AlbumDtoList {
    private List<AlbumDto> albums;

    public AlbumDtoList() {
    }

    public AlbumDtoList(List<AlbumDto> albums) {
        this.albums = albums;
    }

    public List<AlbumDto> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumDto> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "AlbumDtoList{" +
                "albums=" + albums +
                '}';
    }
}
