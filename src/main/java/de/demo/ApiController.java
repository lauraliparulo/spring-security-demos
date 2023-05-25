package de.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

  private final AlbumService albumService;

  public ApiController(AlbumService albumService) {
    this.albumService = albumService;
  }

  @GetMapping("/api/albums")
  public List<Album> all() {
    return albumService.getAlbums();
  }

  @PostMapping("/api/albums")
  public Album newVideo(@RequestBody Album newAlbum) {
    return albumService.create(newAlbum);
  }
}
