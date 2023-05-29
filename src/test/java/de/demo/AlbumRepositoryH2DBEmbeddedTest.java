package de.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import de.demo.entity.album.AlbumEntity;
import de.demo.repo.AlbumRepository;

@DataJpaTest
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.tRUE)
public class AlbumRepositoryH2DBEmbeddedTest {

	@Autowired
	AlbumRepository repository;

	@BeforeEach
	void setUp() {
		repository.saveAll( //
				List.of( //
						new AlbumEntity("Def Leppard", "Hysteria", 1987, "rock", "admin"),
						new AlbumEntity("Poison", "Look what the cat dragged in", 1985, "rock", "admin"),
						new AlbumEntity("Kiss", "Revenge", 1991, "rock", "admin")));
	}

	@Test
	void findAllShouldProduceAllVideos() {
		List<AlbumEntity> albums = repository.findAll();
		assertThat(albums).hasSize(3);
	}

	@Test
	void findByNameShouldRetrieveOneEntry() {
		List<AlbumEntity> albums = repository.findByTitleContainsIgnoreCase("what");
		assertThat(albums).hasSize(1);
		assertThat(albums).extracting(AlbumEntity::getArtist) //
				.containsExactlyInAnyOrder( //
						"Poison");
	}

}
