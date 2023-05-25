package de.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

  private final AlbumService albumService;

  public HomeController(AlbumService albumService) {
    this.albumService = albumService;
  }

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("albums", albumService.getAlbums());
    return "index";
  }

  @GetMapping("/react")
  public String react() {
    return "react";
  }

  @PostMapping("/new-album")
  public String newAlbum(@ModelAttribute Album newAlbum) {
    albumService.create(newAlbum);
    return "redirect:/";
  }
}
