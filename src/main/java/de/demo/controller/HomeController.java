package de.demo.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import de.demo.entity.album.AlbumEntity;
import de.demo.entity.album.AlbumSearchDTO;
import de.demo.entity.album.NewAlbumDTO;
import de.demo.service.AlbumService;


@Controller
public class HomeController {

  private final AlbumService albumService;

  public HomeController(AlbumService albumService) {
    this.albumService = albumService;
  }

  @GetMapping("/")
  public String index(Model model, //
    Authentication authentication) {
    model.addAttribute("albums", albumService.getAlbums());
    model.addAttribute("authentication", authentication);
    return "index";
  }

  @GetMapping("/react")
  public String react() {
    return "react";
  }

  @PostMapping("/new-album")
  public String newAlbum(@ModelAttribute NewAlbumDTO newAlbum,Authentication authentication) {

	    	albumService.create(newAlbum, authentication.getName());
	        return "redirect:/";
  }
  
  
  @PostMapping("/search")
  public String universalSearch(@ModelAttribute AlbumSearchDTO search, //
    Model model, //
    Authentication authentication) {
    List<AlbumEntity> searchResults = albumService.search(search);
    searchResults.forEach(n -> System.out.println("FOUND RECORD: "+n));
    
    model.addAttribute("search", search);
    model.addAttribute("albums", searchResults);
    model.addAttribute("authentication", authentication);
    return "index";
  }
  

  @PostMapping("/delete/albums/{albumId}")
  public String deleteAlbum(@PathVariable Long albumId) {
    albumService.delete(albumId);
    return "redirect:/";
  }
  
  @GetMapping("/loginerror")
  public String loginerror() {
    return "loginerror";
  }
  
  
  @GetMapping("/accessdenied")
  public String accessdenied() {
    return "accessdenied";
  }
}
