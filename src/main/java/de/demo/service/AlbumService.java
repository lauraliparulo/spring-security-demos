package de.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import de.demo.entity.album.Album;
import de.demo.entity.album.AlbumSearchDTO;
import de.demo.entity.album.NewAlbumDTO;
import de.demo.repo.AlbumRepository;
import jakarta.annotation.PostConstruct;

@Service
public class AlbumService {

	@Autowired
	private AlbumRepository repository;

	  @PostConstruct
	  void initDatabase() {
	    repository.save(new Album("Def Leppard", "Hysteria", 1987, "rock","admin"));
	    repository.save(new Album("Poison", "Look what the cat dragged in", 1985, "rock","admin"));
	    repository.save(new Album("Kiss", "Revenge", 1991, "rock","admin"));
	  }
	  
	  
	public List<Album> getAlbums() {
		  return repository.findAll();
	}
	
	  public Album create(NewAlbumDTO newAlbum, String username) {
		    return repository.saveAndFlush(new Album(newAlbum.artist(), newAlbum.title(), newAlbum.yearOfRelease(), newAlbum.genre(), username));
		  }
	  

	  public List<Album> search(AlbumSearchDTO search) {
		  Album probe = new Album();
		    if (StringUtils.hasText(search.value())) {
		      probe.setTitle(search.value());
		      probe.setArtist(search.value());
		      probe.setDescription(search.value());
		    }
		    Example<Album> example = Example.of(probe, //
		      ExampleMatcher.matchingAny() //
		        .withIgnoreCase() //
		        .withStringMatcher(StringMatcher.CONTAINING));
		    return repository.findAll(example);
		  }
	
	
	  @PreAuthorize("hasRole('ADMIN')")
	  public void delete(Long albumId) {
	    repository.findById(albumId) //
	      .map(albumEntity -> {
	        repository.delete(albumEntity);
	        return true;
	      }) //
	      .orElseThrow(() -> new RuntimeException("No album at " + albumId));
	  }
}
