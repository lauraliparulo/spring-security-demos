package de.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import de.demo.model.Album;
import de.demo.repo.AlbumRepository;
import jakarta.annotation.PostConstruct;

@Service
public class AlbumService {

	@Autowired
	private AlbumRepository repository;

	  @PostConstruct
	  void initDatabase() {
	    repository.save(new Album("Def Leppard", "Hysteria", 1987));
	    repository.save(new Album("Poison", "Look what the cat dragged in", 1985));
	    repository.save(new Album("Kiss", "Revenge", 1991));
	  }
	  
	  
	public List<Album> getAlbums() {
		  return repository.findAll();
	}
	
	  public Album create(NewAlbumDTO newAlbum) {
		    return repository.saveAndFlush(new Album(newAlbum.artist(), newAlbum.title(), newAlbum.yearOfRelease()));
		  }
	  

	public List<Album> search(AlbumSearchDTO albumSearch) {
		if (StringUtils.hasText(albumSearch.title()) //
				&& StringUtils.hasText(albumSearch.description())) {
			return repository //
					.findByTitleContainsOrDescriptionContainsAllIgnoreCase( //
							albumSearch.title(), albumSearch.description());
		}

		if (StringUtils.hasText(albumSearch.title())) {
			return repository.findByTitleContainsIgnoreCase(albumSearch.title());
		}

		if (StringUtils.hasText(albumSearch.description())) {
			return repository.findByDescriptionContainsIgnoreCase(albumSearch.description());
		}

		return Collections.emptyList();
	}
}
