package de.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Example;

import de.demo.entity.album.AlbumEntity;
import de.demo.entity.album.AlbumSearchDTO;
import de.demo.entity.album.NewAlbumDTO;
import de.demo.repo.AlbumRepository;
import de.demo.service.AlbumService;

@ExtendWith(MockitoExtension.class)
@Import(TestConfiguration.class)
public class AlbumServiceTest {

	AlbumService service;

	@Mock
	AlbumRepository repository;

	@BeforeEach
	void setUp() {
		this.service = new AlbumService(repository);
	}

	@Test
	void getVideosShouldReturnAll() {
		// given
		AlbumEntity album1 = new AlbumEntity("Oasis", "Def maybe", 1993, "Britpop", null);
		AlbumEntity album2 = new AlbumEntity("Metallica", "Blackalbum", 1990, "metal", null);
		when(repository.findAll()).thenReturn(List.of(album1, album2));

		// when
		List<AlbumEntity> albums = service.getAlbums();

		// then
		assertThat(albums).containsExactly(album1, album2);
	}

	@Test
	void searchShouldReturnASubset() {
		// given
		AlbumEntity album1 = new AlbumEntity("Oasis", "Def maybe", 1993, "Britpop", null);
		AlbumEntity album2 = new AlbumEntity("Metallica", "Blackalbum", 1990, "metal", null);
		when(repository.findAll(any(Example.class))).thenReturn(List.of(album1, album2));

		// when
		List<AlbumEntity> videos = service.search(new AlbumSearchDTO("Oasis"));

		// then
		assertThat(videos).containsExactly(album1,album2);
	}

	@Test
	void creatingANewVideoShouldReturnTheSameData() {
		// given
		given(repository.saveAndFlush(any(AlbumEntity.class))) //
				.willReturn(new AlbumEntity("megadeth", "peace sells", 1986, "metal", "alice"));

		// when
		AlbumEntity newAlbum = service.create(new NewAlbumDTO("megadeth", "peace sells", 1986, "metal"), "alice");

		// then
//    assertThat(newVideo.getName()).isEqualTo("name");
		assertThat(newAlbum.getArtist()).isEqualTo("megadeth");
		assertThat(newAlbum.getUsername()).isEqualTo("alice");
	}

	@Test
	void deletingAVideoShouldWork() {

		// given
		given(repository.saveAndFlush(any(AlbumEntity.class))) //
				.willReturn(new AlbumEntity("megadeth", "peace sells", 1986, "metal", "alice"));

		AlbumEntity newAlbum = service.create(new NewAlbumDTO("megadeth", "peace sells", 1986, "metal"), "alice");
		newAlbum.setId(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(newAlbum));

		// when
		service.delete(1L);

		// then
		verify(repository).findById(1L);
		verify(repository).delete(newAlbum);
	}
}
