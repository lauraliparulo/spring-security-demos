package de.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AlbumService {

  private List<Album> albums = List.of( 
    new Album("Def Leppard","Hysteria",1987), 
    new Album("Poison", "Look what the cat dragged in", 1985), 
    new Album("Kiss", "Revenge",1991));

	public List<Album> getAlbums() {
    return albums;
  }

  public Album create(Album newAlbum) {
    List<Album> extend = new ArrayList<>(albums);
    extend.add(newAlbum);
    this.albums = List.copyOf(extend);
    return newAlbum;
  }
}
