package de.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import de.demo.model.Album_r;
import de.demo.repo.Album;
import de.demo.repo.AlbumRepository;

@Service
public class AlbumService {

	@Autowired
	private AlbumRepository repository;

	private List<Album_r> albums = List.of(new Album_r("Def Leppard", "Hysteria", 1987),
			new Album_r("Poison", "Look what the cat dragged in", 1985), new Album_r("Kiss", "Revenge", 1991));

	public List<Album_r> getAlbums() {
		return albums;
	}

	public Album_r create(Album_r newAlbum) {
		List<Album_r> extend = new ArrayList<>(albums);
		extend.add(newAlbum);
		this.albums = List.copyOf(extend);
		return newAlbum;
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
