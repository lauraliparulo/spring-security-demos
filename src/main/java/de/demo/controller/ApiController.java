package de.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.demo.entity.album.AlbumEntity;
import de.demo.entity.album.NewAlbumDTO;
import de.demo.service.AlbumService;

@RestController
public class ApiController {

  private final AlbumService albumService;

  public ApiController(AlbumService albumService) {
    this.albumService = albumService;
  }

  @GetMapping("/api/albums")
  public List<AlbumEntity> all() {
    return albumService.getAlbums();
  }

  @PostMapping("/api/albums")
  public AlbumEntity newVideo(@RequestBody NewAlbumDTO newAlbum) {
    return albumService.create(newAlbum,null);
  }
}
