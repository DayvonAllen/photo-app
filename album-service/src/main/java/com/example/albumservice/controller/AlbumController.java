package com.example.albumservice.controller;

import com.example.albumservice.api.v1.domain.AlbumDtoList;
import com.example.albumservice.service.AlbumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/albums")
public class AlbumController {
    private final AlbumService albumService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public AlbumDtoList getUsersAlbum(@PathVariable Long id){
        logger.info("Fetching all albums that belongs to user with the id of " + id);
        AlbumDtoList albumDtoList = albumService.getAlbums(id);
        System.out.println(albumDtoList);
        return albumDtoList;
    }
}
