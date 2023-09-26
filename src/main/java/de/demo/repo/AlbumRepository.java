package de.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import de.demo.entity.album.AlbumEntity;
	
public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {

  List<AlbumEntity> findByTitleContainsIgnoreCase(String partialTitle);

  List<AlbumEntity> findByDescriptionContainsIgnoreCase(String partialDescription);
  
  List<AlbumEntity> findByGenreIgnoreCase(String genre);

  List<AlbumEntity> findByTitleContainsOrDescriptionContainsAllIgnoreCase(String partialTitle,
    String partialDescription);

  @PreAuthorize("#entity.username == authentication.name")
  @Override
  void delete(AlbumEntity entity);
}
