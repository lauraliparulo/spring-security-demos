package de.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.demo.model.Album_r;
import de.demo.repo.Album;
import de.demo.service.AlbumSearchDTO;
import de.demo.service.AlbumService;

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
  public String newAlbum(@ModelAttribute Album_r newAlbum) {
    albumService.create(newAlbum);
    return "redirect:/";
  }
  
  
  @PostMapping("/multi-field-search")
  public String multiFieldSearch( //
    @ModelAttribute AlbumSearchDTO search, //
    Model model) {
    List<Album> searchResults = //
    		albumService.search(search);
    model.addAttribute("albums", searchResults);
    return "index";
  }
}
