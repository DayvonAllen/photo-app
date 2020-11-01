package com.example.albumservice.model;

import java.util.Objects;
import java.util.UUID;


public class Album {
    private Long id;
    private Long userId;
    private String name;
    private String description;

    public Album() {
    }

    public Album(Long id, Long userId, String name, String description) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(id, album.id) &&
                Objects.equals(userId, album.userId) &&
                Objects.equals(name, album.name) &&
                Objects.equals(description, album.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, description);
    }
}
