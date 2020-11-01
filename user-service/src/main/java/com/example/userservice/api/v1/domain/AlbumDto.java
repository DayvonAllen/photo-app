package com.example.userservice.api.v1.domain;

public class AlbumDto {
    private Long albumsId;
    private Long userId;
    private String name;
    private String description;

    public AlbumDto() {
    }

    public AlbumDto(Long albumId, Long userId, String name, String description) {
        this.albumsId = albumId;
        this.userId = userId;
        this.name = name;
        this.description = description;
    }

    public Long getAlbumsId() {
        return albumsId;
    }

    public void setAlbumsId(Long albumsId) {
        this.albumsId = albumsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AlbumDto{" +
                "albumId=" + albumsId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
